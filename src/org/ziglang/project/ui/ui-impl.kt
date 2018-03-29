package org.ziglang.project.ui

import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextBrowseFolderListener
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.platform.ProjectGeneratorPeer
import org.ziglang.ZigBundle
import org.ziglang.project.*
import java.awt.event.ItemListener

class ZigProjectGeneratorPeerImpl : ZigProjectGeneratorPeer() {
	private val settings = ZigSettings()
	private val listeners = ArrayList<ProjectGeneratorPeer.SettingsListener>()

	init {
		initExeComboBox(executablePath)
		installPathField.addBrowseFolderListener(TextBrowseFolderListener(
				FileChooserDescriptorFactory.createSingleFolderDescriptor()))
		zigWebsite.asLink()
		iceZigRelease.asLink()
		setupLater.addChangeListener {
			executablePath.isEnabled = !setupLater.isSelected
		}
		val listener = ItemListener {
			val selected = executablePath.comboBox.selectedItem as? String ?: return@ItemListener
			version.text = versionOf(selected)
		}
		listener.itemStateChanged(null)
		executablePath.comboBox.addItemListener(listener)
	}

	@Suppress("OverridingDeprecatedMember")
	override fun addSettingsStateListener(
			@Suppress("DEPRECATION") listener: com.intellij.platform.WebProjectGenerator.SettingsStateListener) = Unit

	override fun addSettingsListener(listener: ProjectGeneratorPeer.SettingsListener) {
		listeners += listener
	}

	override fun getSettings() = settings
	override fun getComponent() = mainPanel
	override fun buildUI(settingsStep: SettingsStep) = settingsStep.addExpertPanel(component)

	override fun validate(): ValidationInfo? {
		if (setupLater.isSelected) return null
		val selected = executablePath.comboBox.selectedItem as? String
		if (selected != null) {
			if (!validateZigExe(selected)) {
				// usefulText.isVisible = true
				return ValidationInfo(ZigBundle.message("zig.project.invalid-exe"))
			}
			val installPath = installPathField.text
			if (!validateZigLib(installPath)) {
				// usefulText.isVisible = true
				return ValidationInfo(ZigBundle.message("zig.project.invalid-install-path"))
			}
			settings.installPath = installPath
			listeners.forEach { it.stateChanged(true) }
			settings.exePath = selected
			settings.version = version.text
			zigGlobalSettings.knownZigExes += selected
		}
		return null
	}

	override fun isBackgroundJobRunning() = false
}

class ZigConfigurableImpl(project: Project) : ZigConfigurable() {
	private val settings = project.zigSettings.settings

	init {
		initExeComboBox(executablePath) {
			version.text = versionOf(it.comboBox.selectedItem as? String ?: return@initExeComboBox)
		}
		zigWebsite.asLink()
		iceZigRelease.asLink()
		installPathField.text = settings.installPath
		version.text = settings.version
		installPathField.addBrowseFolderListener(TextBrowseFolderListener(
				FileChooserDescriptorFactory.createSingleFolderDescriptor()))
	}

	override fun createComponent() = mainPanel
	override fun getDisplayName() = ZigBundle.message("zig.name")
	override fun isModified() = executablePath.comboBox.selectedItem != settings.exePath ||
			version.text != settings.version ||
			installPathField.text != settings.installPath

	@Throws(ConfigurationException::class)
	override fun apply() {
		val selected = executablePath.comboBox.selectedItem as? String
		if (selected != null) {
			if (!validateZigExe(selected))
				throw ConfigurationException(ZigBundle.message("zig.project.invalid-exe"))
			val installPath = installPathField.text
			if (!validateZigLib(installPath))
				throw ConfigurationException(ZigBundle.message("zig.project.invalid-install-path"))
			settings.installPath = installPathField.text
			settings.version = version.text
			settings.exePath = selected
			zigGlobalSettings.knownZigExes += selected
		}
	}
}

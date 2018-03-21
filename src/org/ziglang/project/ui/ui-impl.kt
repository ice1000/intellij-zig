package org.ziglang.project.ui

import com.intellij.ide.browsers.BrowserLauncher
import com.intellij.ide.util.projectWizard.SettingsStep
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
		zigWebsite.setListener({ _, _ ->
			BrowserLauncher.instance.browse(zigWebsite.text)
		}, null)
		setupLater.addActionListener {
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

	override fun getSettings() = settings.apply { initWithExe() }
	override fun getComponent() = mainPanel
	override fun buildUI(settingsStep: SettingsStep) = settingsStep.addExpertPanel(component)

	override fun validate(): ValidationInfo? {
		if (setupLater.isSelected) return null
		val selected = executablePath.comboBox.selectedItem as? String
		return if (selected != null && validateZigExe(selected)) {
			listeners.forEach { it.stateChanged(true) }
			settings.exePath = selected
			zigGlobalSettings.knownZigExes += selected
			null
		} else {
			// usefulText.isVisible = true
			ValidationInfo(ZigBundle.message("zig.project.invalid-exe"))
		}
	}

	override fun isBackgroundJobRunning() = false
}

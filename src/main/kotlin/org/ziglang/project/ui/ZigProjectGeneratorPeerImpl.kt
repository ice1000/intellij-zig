package org.ziglang.project.ui

import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextBrowseFolderListener
import com.intellij.openapi.ui.ValidationInfo
import com.intellij.platform.ProjectGeneratorPeer
import org.ziglang.i18n.ZigBundle
import org.ziglang.project.*
import java.awt.event.ItemListener

class ZigProjectGeneratorPeerImpl : ZigProjectGeneratorPeer() {
    private val settings = ZigSettings()
    private val listeners = ArrayList<ProjectGeneratorPeer.SettingsListener>()

    init {
        initExeComboBox(executablePath) {
            executablePath.selectedItem = settings.exePath
            version.text = versionOf(it.selectedItem as? String ?: return@initExeComboBox)
        }
        installPathField.addBrowseFolderListener(
            TextBrowseFolderListener(
                FileChooserDescriptorFactory.createSingleFolderDescriptor()
            )
        )
        zigWebsite.asLink()
        iceZigRelease.asLink()
        setupLater.addChangeListener {
            executablePath.isEnabled = !setupLater.isSelected
        }
        val listener = ItemListener {
            val selected = executablePath.selectedItem as? String ?: return@ItemListener
            version.text = versionOf(selected)
        }
        listener.itemStateChanged(null)
        executablePath.addItemListener(listener)
    }

    override fun addSettingsListener(listener: ProjectGeneratorPeer.SettingsListener) {
        listeners += listener
    }

    override fun getSettings() = settings
    override fun getComponent() = mainPanel
    override fun buildUI(settingsStep: SettingsStep) = settingsStep.addExpertPanel(component)

    override fun validate(): ValidationInfo? {
        if (setupLater.isSelected) return null
        val selected = executablePath.selectedItem as? String
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


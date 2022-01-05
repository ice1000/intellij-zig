package org.ziglang.project.ui

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.options.ConfigurationException
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextBrowseFolderListener
import org.ziglang.i18n.ZigBundle
import org.ziglang.project.*

class ZigConfigurableImpl(project: Project) : ZigConfigurable() {
    private val settings = project.zigSettingsNullable?.settings ?: ZigSettings()

    init {
        initExeComboBox(executablePath) {
            executablePath.selectedItem = settings.exePath
            version.text = versionOf(it.selectedItem as? String ?: return@initExeComboBox)
        }
        zigWebsite.asLink()
        iceZigRelease.asLink()
        installPathField.text = settings.installPath
        version.text = settings.version
        installPathField.addBrowseFolderListener(
            TextBrowseFolderListener(
                FileChooserDescriptorFactory.createSingleFolderDescriptor()
            )
        )
    }

    override fun createComponent() = mainPanel
    override fun getDisplayName() = ZigBundle.message("zig.name")
    override fun isModified() = executablePath.selectedItem != settings.exePath ||
            version.text != settings.version ||
            installPathField.text != settings.installPath

    @Throws(ConfigurationException::class)
    override fun apply() {
        val selected = executablePath.selectedItem as? String
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
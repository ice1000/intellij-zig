package org.ziglang.project.ui

import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextBrowseFolderListener
import com.intellij.openapi.ui.ValidationInfo
import org.ziglang.ZigBundle
import org.ziglang.project.ZigSettings


class ZigProjectGeneratorPeerImpl : ZigProjectGeneratorPeer() {
	private val settings = ZigSettings()

	init {
		executablePath.addBrowseFolderListener(TextBrowseFolderListener(FileChooserDescriptorFactory.createSingleFileOrExecutableAppDescriptor()))
	}

	@Suppress("OverridingDeprecatedMember")
	override fun addSettingsStateListener(
			@Suppress("DEPRECATION") listener: com.intellij.platform.WebProjectGenerator.SettingsStateListener) = Unit

	override fun getSettings() = settings
	override fun getComponent() = mainPanel
	override fun buildUI(settingsStep: SettingsStep) {
		TODO("not implemented")
	}

	override fun validate(): ValidationInfo? {
		val path = executablePath.text
		return if (true) {    //TODO check the executable is valid
			settings.exePath = path
			null
		} else {
			ValidationInfo(ZigBundle.message("zig.project.invalid"))
		}
	}

	override fun isBackgroundJobRunning() = false
}

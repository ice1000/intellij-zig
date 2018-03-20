package org.ziglang.project.ui

import com.intellij.ide.util.projectWizard.SettingsStep
import com.intellij.openapi.ui.ValidationInfo
import org.ziglang.project.ZigSettings


class ZigProjectGeneratorPeerImpl : ZigProjectGeneratorPeer() {
	private val settings = ZigSettings()
	@Suppress("OverridingDeprecatedMember")
	override fun addSettingsStateListener(
			@Suppress("DEPRECATION") listener: com.intellij.platform.WebProjectGenerator.SettingsStateListener) = Unit

	override fun getSettings() = settings
	override fun getComponent() = mainPanel
	override fun buildUI(settingsStep: SettingsStep) {
		TODO("not implemented")
	}

	override fun validate(): ValidationInfo? {
		return null
	}

	override fun isBackgroundJobRunning() = false
}

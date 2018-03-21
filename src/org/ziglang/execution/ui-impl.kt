package org.ziglang.execution

class ZigRunConfigurationEditorImpl(configuration: ZigRunConfiguration) : ZigRunConfigurationEditor() {
	init {
		resetEditorFrom(configuration)
	}

	override fun createEditor() = mainPanel
	override fun resetEditorFrom(configuration: ZigRunConfiguration) {
	}

	override fun applyEditorTo(configuration: ZigRunConfiguration) {
	}
}

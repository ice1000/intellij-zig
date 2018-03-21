package org.ziglang.execution

class ZigRunConfigurationEditorImpl(configuration: ZigRunConfiguration) : ZigRunConfigurationEditor() {
	init {
		resetEditorFrom(configuration)
	}

	override fun createEditor() = mainPanel
	override fun resetEditorFrom(configuration: ZigRunConfiguration) {
		// TODO swing 泛型编程
	}

	override fun applyEditorTo(configuration: ZigRunConfiguration) {
		// TODO swing 泛型编程
	}
}

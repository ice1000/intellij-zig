package org.ziglang.execution

import org.ziglang.project.initExeComboBox

class ZigRunConfigurationEditorImpl(configuration: ZigRunConfiguration) : ZigRunConfigurationEditor() {
	init {
		resetEditorFrom(configuration)
		initExeComboBox(executablePath)
	}

	override fun createEditor() = mainPanel
	override fun resetEditorFrom(configuration: ZigRunConfiguration) {
		executablePath.comboBox.selectedItem = configuration.exePath
		targetFileField.text = configuration.targetFile
		workingDirField.text = configuration.workingDir
		compilerArgsField.text = configuration.additionalOptions
		programArgsField.text = configuration.programArgs
		installPathField.text = configuration.installPath
	}

	override fun applyEditorTo(configuration: ZigRunConfiguration) {
		configuration.targetFile = targetFileField.text
		configuration.workingDir = workingDirField.text
		configuration.additionalOptions = compilerArgsField.text
		configuration.programArgs = programArgsField.text
		configuration.installPath = installPathField.text
		configuration.exePath = executablePath.comboBox.selectedItem as? String ?: return
	}
}

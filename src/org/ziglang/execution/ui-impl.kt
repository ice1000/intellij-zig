package org.ziglang.execution

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextBrowseFolderListener
import org.ziglang.project.initExeComboBox

class ZigRunConfigurationEditorImpl(configuration: ZigRunConfiguration) : ZigRunConfigurationEditor() {
	init {
		resetEditorFrom(configuration)

		listOf("fast", "safe", "debug").forEach(releaseMode::addItem)

		initExeComboBox(executablePath)
		installPathField.addBrowseFolderListener(TextBrowseFolderListener(
				FileChooserDescriptorFactory.createSingleFolderDescriptor()))
		outputDirField.addBrowseFolderListener(TextBrowseFolderListener(
			FileChooserDescriptorFactory.createSingleFolderDescriptor()))
	}

	override fun createEditor() = mainPanel
	override fun resetEditorFrom(configuration: ZigRunConfiguration) {
		executablePath.comboBox.selectedItem = configuration.exePath
		targetFileField.text = configuration.targetFile
		workingDirField.text = configuration.workingDir
		compilerArgsField.text = configuration.additionalOptions
		programArgsField.text = configuration.programArgs
		installPathField.text = configuration.installPath
		outputDirField.text = configuration.outputDir

		releaseMode.selectedItem = configuration.releaseMode

		statically.isSelected = configuration.static
		strip.isSelected = configuration.strip
	}

	override fun applyEditorTo(configuration: ZigRunConfiguration) {
		configuration.targetFile = targetFileField.text
		configuration.workingDir = workingDirField.text
		configuration.additionalOptions = compilerArgsField.text
		configuration.programArgs = programArgsField.text
		configuration.installPath = installPathField.text
		configuration.outputDir = outputDirField.text
		configuration.exePath = executablePath.comboBox.selectedItem as? String ?: return

		configuration.releaseMode = releaseMode.selectedItem.toString()

		configuration.static = statically.isSelected
		configuration.strip = strip.isSelected
	}
}

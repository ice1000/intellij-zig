package org.ziglang.execution

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.ui.TextBrowseFolderListener
import org.ziglang.ZigFileType
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
		targetFileField.addBrowseFolderListener(TextBrowseFolderListener(
				FileChooserDescriptorFactory.createSingleFileDescriptor(ZigFileType)))
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

		verboseTokenize.isSelected = configuration.verboseTokenize
		verboseParsing.isSelected = configuration.verboseParsing
		verboseLinking.isSelected = configuration.verboseLinking
		verboseCImports.isSelected = configuration.verboseCImports
		verboseZigIR.isSelected = configuration.verboseZigIR
		verboseLlvmIR.isSelected = configuration.verboseLlvmIR
	}

	override fun applyEditorTo(configuration: ZigRunConfiguration) {
		configuration.targetFile = targetFileField.text
		configuration.workingDir = workingDirField.text
		configuration.additionalOptions = compilerArgsField.text
		configuration.programArgs = programArgsField.text
		configuration.installPath = installPathField.text
		configuration.outputDir = outputDirField.text
		configuration.exePath = executablePath.comboBox.selectedItem as String

		configuration.static = statically.isSelected
		configuration.strip = strip.isSelected
		configuration.releaseMode = releaseMode.selectedItem as String

		configuration.verboseTokenize = verboseTokenize.isSelected
		configuration.verboseParsing = verboseParsing.isSelected
		configuration.verboseLinking = verboseLinking.isSelected
		configuration.verboseCImports = verboseCImports.isSelected
		configuration.verboseZigIR = verboseZigIR.isSelected
		configuration.verboseLlvmIR = verboseLlvmIR.isSelected
	}
}

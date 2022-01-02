package org.ziglang.execution

import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFileDescriptor
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory.createSingleFolderDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.TextBrowseFolderListener
import org.ziglang.ZigFileType
import org.ziglang.execution.ui.ZigRunConfigurationEditor
import org.ziglang.project.initExeComboBox
import org.ziglang.project.zigGlobalSettings

class ZigRunConfigurationEditorImpl(configuration: ZigRunConfiguration, project: Project) :
    ZigRunConfigurationEditor() {
    init {
        installPathField.addBrowseFolderListener(TextBrowseFolderListener(createSingleFolderDescriptor()))
        outputDirField.addBrowseFolderListener(TextBrowseFolderListener(createSingleFolderDescriptor()))
        workingDirField.addBrowseFolderListener(TextBrowseFolderListener(createSingleFolderDescriptor()))
        targetFileField.addBrowseFolderListener(TextBrowseFolderListener(createSingleFileDescriptor(ZigFileType)))

        initExeComboBox(executablePath, project)

        resetEditorFrom(configuration)
    }

    override fun createEditor() = mainPanel

    override fun resetEditorFrom(configuration: ZigRunConfiguration) {
        executablePath.selectedItem = configuration.exePath
        targetFileField.text = configuration.targetFile
        workingDirField.text = configuration.workingDir
        compilerArgsField.text = configuration.additionalOptions
        programArgsField.text = configuration.programArgs
        installPathField.text = configuration.installPath
        outputDirField.text = configuration.outputDir

        releaseMode.selectedItem = configuration.releaseMode
        coloredMode.selectedItem = configuration.coloredMode
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

        configuration.static = statically.isSelected
        configuration.strip = strip.isSelected
        configuration.releaseMode = releaseMode.selectedItem as String
        configuration.coloredMode = coloredMode.selectedItem as String

        configuration.verboseTokenize = verboseTokenize.isSelected
        configuration.verboseParsing = verboseParsing.isSelected
        configuration.verboseLinking = verboseLinking.isSelected
        configuration.verboseCImports = verboseCImports.isSelected
        configuration.verboseZigIR = verboseZigIR.isSelected
        configuration.verboseLlvmIR = verboseLlvmIR.isSelected

        val executablePath = executablePath.selectedItem as String
        if (!zigGlobalSettings.knownZigExes.contains(executablePath)) {
            zigGlobalSettings.knownZigExes.add(executablePath)
        }

        configuration.exePath = executablePath
    }
}

@file:Suppress("DEPRECATION")

package org.ziglang.execution

import com.intellij.execution.Executor
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.LocatableConfigurationBase
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.components.PathMacroManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizer
import org.jdom.Element
import org.ziglang.ZigBundle
import org.ziglang.icons.ZigIcons
import org.ziglang.project.zigSettings

class ZigRunConfiguration(project: Project, factory: ConfigurationFactory) :
    LocatableConfigurationBase<ZigCommandLineState>(project, factory, ZigBundle.message("zig.name")), DumbAware {

    var exePath: String
    var targetFile = ""
    var workingDir = ""
    var additionalOptions = ""
    var programArgs = ""
    var installPath: String
    var outputDir = ""
    var releaseMode = "debug"
    var coloredMode = "auto"

    var static = false
    var strip = false
    var verboseTokenize = false
    var verboseParsing = false
    var verboseLinking = false
    var verboseCImports = false
    var verboseZigIR = false
    var verboseLlvmIR = false

    var isBuildOnly = false      // Separate "Build" and "Run"

    init {
        val zigSettings = project.zigSettings.settings
        exePath = zigSettings.exePath
        installPath = zigSettings.installPath
    }

    override fun getState(executor: Executor, environment: ExecutionEnvironment) =
        ZigCommandLineState(this@ZigRunConfiguration, isBuildOnly, environment)

    override fun getIcon() = ZigIcons.ZIG_WEBSITE_ICON

    override fun getConfigurationEditor() = ZigRunConfigurationEditorImpl(this, project)

    override fun readExternal(element: Element) {
        super.readExternal(element)
        PathMacroManager.getInstance(project).expandPaths(element)
        JDOMExternalizer.readString(element, "exePath")?.let { exePath = it }
        JDOMExternalizer.readString(element, "targetFile")?.let { targetFile = it }
        JDOMExternalizer.readString(element, "workingDir")?.let { workingDir = it }
        JDOMExternalizer.readString(element, "additionalOptions")?.let { additionalOptions = it }
        JDOMExternalizer.readString(element, "programArgs")?.let { programArgs = it }
        JDOMExternalizer.readString(element, "installPath")?.let { installPath = it }
        JDOMExternalizer.readString(element, "outputDir")?.let { outputDir = it }
        JDOMExternalizer.readString(element, "releaseMode")?.let { releaseMode = it }
        JDOMExternalizer.readString(element, "coloredMode")?.let { coloredMode = it }

        JDOMExternalizer.readBoolean(element, "static").let { static = it }
        JDOMExternalizer.readBoolean(element, "strip").let { strip = it }
        JDOMExternalizer.readBoolean(element, "verboseTokenize").let { verboseTokenize = it }
        JDOMExternalizer.readBoolean(element, "verboseParsing").let { verboseParsing = it }
        JDOMExternalizer.readBoolean(element, "verboseLinking").let { verboseLinking = it }
        JDOMExternalizer.readBoolean(element, "verboseCImports").let { verboseCImports = it }
        JDOMExternalizer.readBoolean(element, "verboseZigIR").let { verboseZigIR = it }
        JDOMExternalizer.readBoolean(element, "verboseLlvmIR").let { verboseLlvmIR = it }
    }

    override fun writeExternal(element: Element) {
        super.writeExternal(element)
        JDOMExternalizer.write(element, "exePath", exePath)
        JDOMExternalizer.write(element, "targetFile", targetFile)
        JDOMExternalizer.write(element, "workingDir", workingDir)
        JDOMExternalizer.write(element, "additionalOptions", additionalOptions)
        JDOMExternalizer.write(element, "programArgs", programArgs)
        JDOMExternalizer.write(element, "installPath", installPath)
        JDOMExternalizer.write(element, "outputDir", outputDir)
        JDOMExternalizer.write(element, "releaseMode", releaseMode)
        JDOMExternalizer.write(element, "coloredMode", coloredMode)

        JDOMExternalizer.write(element, "static", static)
        JDOMExternalizer.write(element, "strip", strip)
        JDOMExternalizer.write(element, "verboseTokenize", verboseTokenize)
        JDOMExternalizer.write(element, "verboseParsing", verboseParsing)
        JDOMExternalizer.write(element, "verboseLinking", verboseLinking)
        JDOMExternalizer.write(element, "verboseCImports", verboseCImports)
        JDOMExternalizer.write(element, "verboseZigIR", verboseZigIR)
        JDOMExternalizer.write(element, "verboseLlvmIR", verboseLlvmIR)
        PathMacroManager.getInstance(project).collapsePathsRecursively(element)
    }
}


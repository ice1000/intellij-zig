package org.ziglang.execution

import com.intellij.execution.DefaultExecutionResult
import com.intellij.execution.ExecutionBundle
import com.intellij.execution.ExecutionResult
import com.intellij.execution.Executor
import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.configurations.RunProfileState
import com.intellij.execution.filters.TextConsoleBuilderFactory
import com.intellij.execution.process.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.execution.runners.ProgramRunner
import com.intellij.execution.ui.ConsoleView
import com.intellij.icons.AllIcons
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.ToggleAction
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.search.ExecutionSearchScopes
import java.nio.file.Paths

class ZigCommandLineState(
    private val configuration: ZigRunConfiguration, private val isBuildOnly: Boolean, env: ExecutionEnvironment
) : RunProfileState {
    private val consoleBuilder = TextConsoleBuilderFactory.getInstance().createBuilder(
            env.project, ExecutionSearchScopes.executionScope(env.project, env.runProfile)
        )

    override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
        ApplicationManager.getApplication().runWriteAction {
            VfsUtil.createDirectoryIfMissing(configuration.outputDir)
        }

        val outputFile = Paths.get(configuration.outputDir, configuration.name).toString()

        val buildParams = buildParams(outputFile)
        val buildHandler = OSProcessHandler(GeneralCommandLine(buildParams).withWorkDirectory(configuration.workingDir))

        val console = consoleBuilder.console
        console.allowHeavyFilters()
        
        if (!isBuildOnly) { 
            buildHandler.addProcessListener(object : ProcessAdapter() {
                override fun processTerminated(event: ProcessEvent) {
                    if (event.exitCode == 0) {
                        console.clear()
                        val params = mutableListOf<String>()
                        with(configuration) {
                            params += outputFile
                            params += programArgs.split(' ', '\n').filter(String::isNotBlank)
                        }
                        val runHandler = OSProcessHandler(
                            GeneralCommandLine(params).withWorkDirectory(configuration.workingDir)
                        )
                        ProcessTerminatedListener.attach(runHandler)
                        console.attachToProcess(runHandler)
                        runHandler.startNotify()
                    }
                }
            })
        }
        
        console.attachToProcess(buildHandler)
        ProcessTerminatedListener.attach(buildHandler)
        buildHandler.startNotify()
        
        return DefaultExecutionResult(console, buildHandler, PauseOutputAction(console, buildHandler))
    }

    private fun buildParams(outputFile: String): MutableList<String> {
        val buildParams = mutableListOf<String>()
        
        with(configuration) {
            buildParams += exePath
            buildParams += "build-exe"
            buildParams += targetFile
            buildParams += additionalOptions.split(' ', '\n').filter(String::isNotBlank)

            when (releaseMode) {
                "fast" -> buildParams += "--release-fast"
                "safe" -> buildParams += "--release-safe"
            }

            if (static) buildParams += "--static"
            if (strip) buildParams += "--strip"
            if (verboseTokenize) buildParams += "--verbose-tokenize"
            if (verboseParsing) buildParams += "--verbose-ast"
            if (verboseLinking) buildParams += "--verbose-link"
            if (verboseCImports) buildParams += "--verbose-cimport"
            if (verboseZigIR) buildParams += "--verbose-ir"
            if (verboseLlvmIR) buildParams += "--verbose-llvm-ir"
        }
        
        return buildParams
    }

    private class PauseOutputAction(private val console: ConsoleView, private val handler: ProcessHandler) :
        ToggleAction(
            ExecutionBundle.message("run.configuration.pause.output.action.name"), null, AllIcons.Actions.Pause
        ), DumbAware {
        override fun isSelected(event: AnActionEvent) = console.isOutputPaused
        override fun setSelected(event: AnActionEvent, flag: Boolean) {
            console.isOutputPaused = flag
            ApplicationManager.getApplication().invokeLater { update(event) }
        }

        override fun update(event: AnActionEvent) {
            super.update(event)
            when {
                !handler.isProcessTerminated -> event.presentation.isEnabled = true
                !console.canPause() || !console.hasDeferredOutput() -> event.presentation.isEnabled = false
                else -> {
                    event.presentation.isEnabled = true
                    console.performWhenNoDeferredOutput { update(event) }
                }
            }
        }
    }
}
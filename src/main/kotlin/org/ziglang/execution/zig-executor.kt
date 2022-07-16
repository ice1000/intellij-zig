package org.ziglang.execution

import com.intellij.execution.*
import com.intellij.execution.configurations.*
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
import org.ziglang.ZIG_INSTALL_PREFIX
import java.nio.file.Paths

class ZigCommandLineState(
		private val configuration: ZigRunConfiguration,
		private val isBuildOnly: Boolean,
		env: ExecutionEnvironment) : RunProfileState {
	private val consoleBuilder = TextConsoleBuilderFactory
			.getInstance()
			.createBuilder(env.project,
					SearchScopeProvider.createSearchScope(env.project, env.runProfile))

	override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
		ApplicationManager.getApplication().runWriteAction {
			VfsUtil.createDirectoryIfMissing(configuration.outputDir)
		}
		val buildParams = mutableListOf<String>()
		val outputFile = Paths.get(
				configuration.outputDir,
				configuration.name
		).toString()
		with(configuration) {
			buildParams += exePath
			buildParams += "build-exe"
			buildParams += targetFile
			buildParams += ZIG_INSTALL_PREFIX
			buildParams += installPath
			buildParams += "--output"
			buildParams += outputFile
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
		val buildHandler = OSProcessHandler(GeneralCommandLine(buildParams)
				.withWorkDirectory(configuration.workingDir))
		val console = consoleBuilder.console
		console.allowHeavyFilters()
		if (!isBuildOnly) buildHandler.addProcessListener(object : ProcessAdapter() {
			override fun processTerminated(event: ProcessEvent) {
				if (event.exitCode == 0) {
					console.clear()
					val params = mutableListOf<String>()
					with(configuration) {
						params += outputFile
						params += programArgs.split(' ', '\n').filter(String::isNotBlank)
					}
					val runHandler = OSProcessHandler(GeneralCommandLine(params)
							.withWorkDirectory(configuration.workingDir))
					ProcessTerminatedListener.attach(runHandler)
					console.attachToProcess(runHandler)
					runHandler.startNotify()
				}
			}
		})
		console.attachToProcess(buildHandler)
		ProcessTerminatedListener.attach(buildHandler)
		buildHandler.startNotify()
		return DefaultExecutionResult(console, buildHandler, PauseOutputAction(console, buildHandler))
	}

	private class PauseOutputAction(private val console: ConsoleView, private val handler: ProcessHandler) :
			ToggleAction(
					ExecutionBundle.message("run.configuration.pause.output.action.name"),
					null, AllIcons.Actions.Pause), DumbAware {
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
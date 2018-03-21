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

class ZigCommandLineState(
		private val configuration: ZigRunConfiguration,
		env: ExecutionEnvironment) : RunProfileState {
	private val consoleBuilder = TextConsoleBuilderFactory
			.getInstance()
			.createBuilder(env.project,
					SearchScopeProvider.createSearchScope(env.project, env.runProfile))

	override fun execute(executor: Executor, runner: ProgramRunner<*>): ExecutionResult {
		val params = mutableListOf<String>()
		with(configuration) {
			params += exePath
			params += "build-exe"
			params += targetFile
			params += additionalOptions.split(' ', '\n').filter(String::isNotBlank)
			// params += "&&"
			// params += targetFile
			// params += programArgs.split(' ', '\n').filter(String::isNotBlank)
		}
		val handler = OSProcessHandler(GeneralCommandLine(params)
				.withWorkDirectory(configuration.workingDir))
		ProcessTerminatedListener.attach(handler)
		val console = consoleBuilder.console
		console.attachToProcess(handler)
		handler.startNotify()
		return DefaultExecutionResult(console, handler, PauseOutputAction(console, handler))
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
				!console.canPause() or !console.hasDeferredOutput() -> event.presentation.isEnabled = false
				else -> {
					event.presentation.isEnabled = true
					console.performWhenNoDeferredOutput { update(event) }
				}
			}
		}
	}
}

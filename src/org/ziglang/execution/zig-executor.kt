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
		val baseDir = configuration.project.baseDir
		val buildParams = mutableListOf<String>()
		with(configuration) {
			buildParams += exePath
			buildParams += "build-exe"
			buildParams += targetFile
			buildParams += "--zig-install-prefix"
			buildParams += installPath
			// TODO 把输出目录改成一个『默认 `baseDir/out/` ，然后可以自己改』的值
			// buildParams += "--output"
			// buildParams += baseDir.toString()
			buildParams += additionalOptions.split(' ', '\n').filter(String::isNotBlank)
		}
		val buildHandler = OSProcessHandler(GeneralCommandLine(buildParams)
				.withWorkDirectory(configuration.workingDir))
		val console = consoleBuilder.console
		buildHandler.addProcessListener(object : ProcessAdapter() {
			override fun processTerminated(event: ProcessEvent) {
				if (event.exitCode == 0) {
					console.clear()
					val params = mutableListOf<String>()
					with(configuration) {
						// TODO 改成和上面那个东西相同的输出目录
						baseDir.findChild("main")?.run { params += path }
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
				!console.canPause() or !console.hasDeferredOutput() -> event.presentation.isEnabled = false
				else -> {
					event.presentation.isEnabled = true
					console.performWhenNoDeferredOutput { update(event) }
				}
			}
		}
	}
}

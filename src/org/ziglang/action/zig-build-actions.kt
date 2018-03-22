package org.ziglang.action

import com.google.common.io.Files
import com.intellij.execution.*
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.ziglang.ZigBundle
import org.ziglang.execution.ZigRunConfiguration
import org.ziglang.execution.ZigRunConfigurationType
import org.ziglang.trimPath

class ZigBuildAction : ZigAction(
		ZigBundle.message("zig.actions.build.title"),
		ZigBundle.message("zig.actions.build.description")) {
	override fun actionPerformed(e: AnActionEvent) {
		val project = e.project ?: return
		val file = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext) ?: return
		val path = file.path.trimPath()
		val executor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID)
		val runnerAndConfigurationSettings = RunManager
				.getInstance(project)
				.createRunConfiguration(
						Files.getNameWithoutExtension(path),
						ZigRunConfigurationType.configurationFactories[0])
				.apply { (configuration as ZigRunConfiguration).targetFile = path }
		ProgramRunnerUtil.executeConfiguration(runnerAndConfigurationSettings, executor)
	}
}

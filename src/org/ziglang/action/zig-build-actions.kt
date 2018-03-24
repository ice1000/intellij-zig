package org.ziglang.action

import com.intellij.execution.*
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.ziglang.ZigBundle
import org.ziglang.execution.ZigRunConfiguration
import org.ziglang.execution.ZigRunConfigurationType
import org.ziglang.trimPath
import com.google.common.io.Files as GoogleFiles

class ZigBuildAction : ZigAction(
		ZigBundle.message("zig.actions.build.title"),
		ZigBundle.message("zig.actions.build.description")) {
	override fun actionPerformed(e: AnActionEvent) {
		val project = e.project ?: return
		val file = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext) ?: return
		val path = file.path.trimPath()
		val executor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID)
		val configuration = RunManager
				.getInstance(project)
				.createRunConfiguration(
						"build${GoogleFiles.getNameWithoutExtension(path)}",
						ZigRunConfigurationType.configurationFactories[0])
				.apply {
					(configuration as ZigRunConfiguration).apply {
						targetFile = path
						isBuildOnly = true
					}
				}
		ProgramRunnerUtil.executeConfiguration(configuration, executor)
	}
}

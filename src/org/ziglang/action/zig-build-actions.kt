package org.ziglang.action

import com.google.common.io.Files
import com.intellij.compiler.actions.CompileActionBase
import com.intellij.execution.*
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.actionSystem.DataContext
import com.intellij.openapi.project.Project
import org.ziglang.ZigBundle
import org.ziglang.execution.ZigRunConfiguration
import org.ziglang.execution.ZigRunConfigurationType
import org.ziglang.trimPath

class ZigBuildAction : CompileActionBase() {
	init {
		templatePresentation.run {
			text = ZigBundle.message("zig.actions.build.title")
			description = ZigBundle.message("zig.actions.build.description")
		}
	}

	override fun doAction(dataContext: DataContext, project: Project) {
		val file = CommonDataKeys.VIRTUAL_FILE.getData(dataContext) ?: return
		val path = file.path.trimPath()
		val executor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID)
		val configuration = RunManager
				.getInstance(project)
				.createRunConfiguration(
						Files.getNameWithoutExtension(path),
						ZigRunConfigurationType.configurationFactories[0])
				.apply { (configuration as ZigRunConfiguration).targetFile = path }
		ProgramRunnerUtil.executeConfiguration(configuration, executor)
	}
}

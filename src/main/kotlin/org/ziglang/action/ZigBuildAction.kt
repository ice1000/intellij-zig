package org.ziglang.action

import com.google.common.io.Files
import com.intellij.execution.ExecutorRegistry
import com.intellij.execution.ProgramRunnerUtil
import com.intellij.execution.RunManager
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.ziglang.ZigBundle
import org.ziglang.execution.ZigRunConfiguration
import org.ziglang.execution.ZigRunConfigurationType
import org.ziglang.trimPath

class ZigBuildAction : ZigAction(
    ZigBundle.message("zig.actions.build.title"),
    ZigBundle.message("zig.actions.build.description")
) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val file = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext) ?: return
        val path = file.path.trimPath()
        val executor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID)
        val configuration = RunManager.getInstance(project)
            .let {
                it.createConfiguration(
                    "build${Files.getNameWithoutExtension(path)}",
                    ZigRunConfigurationType.configurationFactories[0]
                ).apply {
                    it.addConfiguration(this)
                    (configuration as ZigRunConfiguration).apply {
                        targetFile = path
                        isBuildOnly = true
                    }
                }

            }
        ProgramRunnerUtil.executeConfiguration(configuration, executor!!)
    }
}
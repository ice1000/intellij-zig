package org.ziglang.execution

import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.execution.configurations.ConfigurationType
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import java.nio.file.Paths

class ZigRunConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type), DumbAware {
    override fun getId(): String = "Zig"

    override fun createTemplateConfiguration(project: Project) = ZigRunConfiguration(project, this).apply {
        project.baseDir.run {
            workingDir = path
            outputDir = Paths.get(canonicalPath, "out").toString()
        }
    }
}
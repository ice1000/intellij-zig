package org.ziglang.execution

import com.intellij.execution.configurations.ConfigurationType
import com.intellij.openapi.project.DumbAware
import org.ziglang.ZIG_RUN_CONFIG_ID
import org.ziglang.ZigBundle
import org.ziglang.icons.ZigIcons

object ZigRunConfigurationType : ConfigurationType, DumbAware {
    private val factories = arrayOf(ZigRunConfigurationFactory(this))
    override fun getIcon() = ZigIcons.ZIG_BIG_ICON
    override fun getConfigurationTypeDescription() = ZigBundle.message("zig.run-config.description")
    override fun getDisplayName() = ZigBundle.message("zig.name")
    override fun getConfigurationFactories() = factories
    override fun getId() = ZIG_RUN_CONFIG_ID
}
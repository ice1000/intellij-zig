package org.ziglang.execution

import com.google.common.io.Files
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import org.ziglang.ZigFileType
import org.ziglang.project.validateZigExe
import org.ziglang.util.trimPath

class ZigRunConfigurationProducer : LazyRunConfigurationProducer<ZigRunConfiguration>() {
    override fun isConfigurationFromContext(configuration: ZigRunConfiguration, context: ConfigurationContext) =
        configuration.targetFile == VIRTUAL_FILE.getData(context.dataContext)?.run { path.trimPath() }

    private fun validate(configuration: ZigRunConfiguration, ctx: ConfigurationContext) =
        VIRTUAL_FILE.getData(ctx.dataContext)?.fileType == ZigFileType && validateZigExe(configuration.exePath)

    override fun setupConfigurationFromContext(cfg: ZigRunConfiguration, ctx: ConfigurationContext, e: Ref<PsiElement>): Boolean {
        VIRTUAL_FILE.getData(ctx.dataContext)?.path.orEmpty().trimPath().let {
            cfg.targetFile = it
            cfg.name = Files.getNameWithoutExtension(cfg.targetFile)
        }

        return validate(cfg, ctx)
    }

    override fun getConfigurationFactory(): ConfigurationFactory = ZigRunConfigurationType.configurationFactories[0]
}
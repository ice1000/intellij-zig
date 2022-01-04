package org.ziglang.execution

import com.google.common.io.Files
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.LazyRunConfigurationProducer
import com.intellij.execution.configurations.ConfigurationFactory
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import org.ziglang.ZigFileType
import org.ziglang.project.validateZigExe
import org.ziglang.trimPath

class ZigRunConfigurationProducer : LazyRunConfigurationProducer<ZigRunConfiguration>() {
    override fun isConfigurationFromContext(configuration: ZigRunConfiguration, context: ConfigurationContext) =
        configuration.targetFile == CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.run { path.trimPath() }

    private fun validate(configuration: ZigRunConfiguration, context: ConfigurationContext) =
        CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.fileType == ZigFileType
                && validateZigExe(configuration.exePath)

    override fun setupConfigurationFromContext(
        configuration: ZigRunConfiguration, context: ConfigurationContext, sourceElement: Ref<PsiElement>
    ): Boolean {
        CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)
            ?.path
            .orEmpty()
            .trimPath()
            .let {
                configuration.targetFile = it
                configuration.name = Files.getNameWithoutExtension(configuration.targetFile)
            }

        return validate(configuration, context)
    }

    override fun getConfigurationFactory(): ConfigurationFactory = ZigRunConfigurationType.configurationFactories[0]
}
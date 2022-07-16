package org.ziglang.execution

import com.intellij.execution.Executor
import com.intellij.execution.Location
import com.intellij.execution.PsiLocation
import com.intellij.execution.RunManagerEx
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.lineMarker.RunLineMarkerContributor
import com.intellij.execution.runners.ExecutionUtil
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.impl.SimpleDataContext
import com.intellij.psi.PsiElement
import icons.ZigIcons
import org.ziglang.psi.ZigFnDeclaration

class ZigRunMainLineMarkerProvider : RunLineMarkerContributor() {

    override fun getInfo(psiElement: PsiElement): Info? {
        if (psiElement is ZigFnDeclaration) {
            val functionName = psiElement.fnProto.name
            if (functionName == "main") {
                return Info(ZigRunMainAction(psiElement))
            }
        }
        return null
    }

}

class ZigRunMainAction(private val target: PsiElement) : AnAction("Run Main", "Run main function", ZigIcons.RUN_ICON) {
    override fun actionPerformed(event: AnActionEvent) {
        val dataContext = SimpleDataContext.getSimpleContext(Location.DATA_KEY, PsiLocation(target), event.dataContext)
        val context = ConfigurationContext.getFromContext(dataContext, event.place)
        val producer = ZigRunConfigurationProducer()
        val configuration = producer.findOrCreateConfigurationFromContext(context)?.configurationSettings ?: return
        (context.runManager as RunManagerEx).setTemporaryConfiguration(configuration)
        ExecutionUtil.runConfiguration(configuration, Executor.EXECUTOR_EXTENSION_NAME.extensionList.first())
    }
}

package org.ziglang.editing

import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
import org.ziglang.ZigLanguage
import org.ziglang.psi.*

class ZigBreadcrumbsProvider : BreadcrumbsProvider {
    private fun ZigFnProto.text() = name?.let { "$it()" }
    override fun getLanguages() = arrayOf(ZigLanguage.INSTANCE)
    override fun getElementInfo(element: PsiElement) = cutText(
        when (element) {
            is ZigFnDeclaration -> element.fnProto.text()
            is ZigExternDeclaration -> PsiTreeUtil.findChildOfType(element, ZigFnProto::class.java)?.text()
            is ZigTestDeclaration -> element.string.text
            is ZigCompTimeBlock -> "comptime"
            is ZigBlockBlock -> element.firstChild.text
            is ZigBlockExpr -> "{…}"
            is ZigGlobalVarDeclaration -> element.variableDeclaration.name
            is ZigVariableDeclaration -> element.name
            else -> null
        }.orEmpty(), TEXT_MAX
    )

    override fun acceptElement(element: PsiElement) = element is ZigFnDeclaration ||
            element is ZigTestDeclaration ||
            element is ZigExternDeclaration ||
            element is ZigCompTimeBlock ||
            element is ZigBlockBlock ||
            element is ZigBlockExpr ||
            element is ZigGlobalVarDeclaration ||
            element is ZigVariableDeclaration
}
package org.ziglang.editing

import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.util.FileContentUtil
import org.ziglang.ZigBundle

class ZigRemoveElementIntention(
    private val element: PsiElement,
    private val info: String
) : IntentionAction {
    override fun getText() = info
    override fun getFamilyName() = ZigBundle.message("zig.name")
    override fun isAvailable(project: Project, editor: Editor?, file: PsiFile?) = true
    override fun startInWriteAction() = true
    override fun invoke(project: Project, editor: Editor?, file: PsiFile?) {
        if (element.isValid) ApplicationManager.getApplication().runWriteAction {
            val virtualFile = element.containingFile.virtualFile
            element.delete()
            FileContentUtil.reparseFiles(project, listOf(virtualFile), true)
        }
    }
}


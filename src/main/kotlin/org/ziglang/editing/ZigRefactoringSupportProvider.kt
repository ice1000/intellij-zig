package org.ziglang.editing

import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.psi.PsiElement

class ZigRefactoringSupportProvider : RefactoringSupportProvider() {
    override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?) = true
}
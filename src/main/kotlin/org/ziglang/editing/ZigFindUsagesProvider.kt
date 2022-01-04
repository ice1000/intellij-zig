package org.ziglang.editing

import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.PsiNamedElement
import org.ziglang.ZigLexerAdapter
import org.ziglang.ZigTokenType

class ZigFindUsagesProvider : FindUsagesProvider {
    override fun canFindUsagesFor(element: PsiElement) = element is PsiNameIdentifierOwner
    override fun getHelpId(psiElement: PsiElement): String? = null
    override fun getType(element: PsiElement) = ""
    override fun getDescriptiveName(element: PsiElement) = (element as? PsiNamedElement)?.name ?: ""
    override fun getNodeText(element: PsiElement, useFullName: Boolean) = getDescriptiveName(element)
    override fun getWordsScanner() =
        DefaultWordsScanner(ZigLexerAdapter(), ZigTokenType.IDENTIFIERS, ZigTokenType.COMMENTS, ZigTokenType.STRINGS)
}
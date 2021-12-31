package org.ziglang.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.SyntaxTraverser
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import org.ziglang.psi.ZigExpr
import org.ziglang.psi.ZigSymbol

/**
 * @param self The declaration itself
 */
fun collectFrom(startPoint: PsiElement, name: String, self: PsiElement? = null) = SyntaxTraverser
    .psiTraverser(startPoint)
    .filter { it is ZigSymbol && !it.isDeclaration && it.text == name && it != self }
    .mapNotNull(PsiElement::getReference)
    .let { if (self != null) it.filter { it.isReferenceTo(self) } else it }
    .toTypedArray()

fun PsiElement.prevSiblingTypeIgnoring(
    type: IElementType,
    vararg types: IElementType
): PsiElement? {
    var next: PsiElement? = prevSibling
    while (true) {
        val localNext = next ?: return null
        next = localNext.prevSibling
        return if (types.any { localNext.node.elementType == it }) continue
        else localNext.takeIf { it.node.elementType == type }
    }
}

inline fun <reified Psi : PsiElement> PsiElement.prevSiblingIgnoring(vararg types: IElementType): Psi? {
    var next: PsiElement? = prevSibling
    while (true) {
        val localNext = next ?: return null
        next = localNext.prevSibling
        return if (types.any { localNext.node.elementType == it }) continue
        else localNext as? Psi
    }
}

fun PsiElement.firstExprOrNull() = PsiTreeUtil.findChildOfType(this, ZigExpr::class.java)

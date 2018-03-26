package org.ziglang.psi.impl

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import org.ziglang.psi.ZigExpr

fun PsiElement.prevSiblingTypeIgnoring(
		type: IElementType,
		vararg types: IElementType): PsiElement? {
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

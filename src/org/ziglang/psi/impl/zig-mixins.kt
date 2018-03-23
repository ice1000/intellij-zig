package org.ziglang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner
import org.ziglang.orFalse
import org.ziglang.psi.*

interface IZigSymbol : PsiNameIdentifierOwner {
	val isFunctionName: Boolean
}

abstract class ZigSymbolMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigSymbol {
	override val isFunctionName: Boolean
		get() = parent is ZigFnProto && prevSibling?.run { node.elementType == ZigTypes.FN_KEYWORD }.orFalse()
}

package org.ziglang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.TokenType
import org.ziglang.ZigTokenType
import org.ziglang.psi.*

interface IZigSymbol : PsiNameIdentifierOwner {
	val isFunctionName: Boolean
}

abstract class ZigSymbolMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigSymbol {
	override val isFunctionName: Boolean
		get() = parent is ZigFnProto && prevSiblingTypeIgnoring(
				ZigTypes.FN_KEYWORD,
				TokenType.WHITE_SPACE,
				ZigTokenType.LINE_COMMENT) != null

	override fun getNameIdentifier() = this
	override fun setName(name: String) = replace(ZigTokenType.fromText(name, project))
	override fun getName() = text
}

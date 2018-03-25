package org.ziglang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiNameIdentifierOwner
import com.intellij.psi.TokenType
import org.ziglang.ZigTokenType
import org.ziglang.psi.*

interface IZigSymbol : PsiNameIdentifierOwner {
	val isFunctionName: Boolean
	val isBuiltinFunctionName: Boolean
	val isVariableName: Boolean
	val isDeclaration: Boolean
}

abstract class ZigSymbolMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigSymbol {
	final override val isFunctionName: Boolean
		get() = parent is ZigFnProto && prevSiblingTypeIgnoring(
				ZigTypes.FN_KEYWORD,
				TokenType.WHITE_SPACE,
				ZigTokenType.LINE_COMMENT) != null

	/** usage, not declaration */
	final override val isBuiltinFunctionName: Boolean
		get() = parent is ZigMacroExpr

	final override val isVariableName: Boolean
		get() = parent is ZigVariableDeclaration && prevSiblingTypeIgnoring(
				ZigTypes.CONST_KEYWORD,
				TokenType.WHITE_SPACE,
				ZigTokenType.LINE_COMMENT) ?: prevSiblingTypeIgnoring(
				ZigTypes.VAR_KEYWORD,
				TokenType.WHITE_SPACE,
				ZigTokenType.LINE_COMMENT) != null

	override val isDeclaration: Boolean
		get() = isFunctionName or
				isVariableName

	override fun getNameIdentifier() = this
	override fun setName(name: String) = replace(ZigTokenType.fromText(name, project))
	override fun getName() = text
}

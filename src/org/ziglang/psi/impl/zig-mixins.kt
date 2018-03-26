package org.ziglang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import org.ziglang.ZigTokenType
import org.ziglang.psi.*

abstract class TrivialDeclaration(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
	override fun getNameIdentifier() = PsiTreeUtil.findChildOfType(this, ZigSymbol::class.java)
	override fun setName(name: String) = replace(ZigTokenType.fromText(name, project))
	override fun getName() = text
}

interface IZigSymbol : PsiNameIdentifierOwner {
	val isFunctionName: Boolean
	val isBuiltinFunctionName: Boolean
	val isVariableName: Boolean
	val isDeclaration: Boolean
}

abstract class ZigSymbolMixin(node: ASTNode) : TrivialDeclaration(node), ZigSymbol {
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
}

abstract class ZigVariableDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigVariableDeclaration {
}

abstract class ZigFnProtoMixin(node: ASTNode) : TrivialDeclaration(node), ZigVariableDeclaration {
}

package org.ziglang.editing

import com.intellij.psi.PsiElement
import org.ziglang.ZigFile
import org.ziglang.psi.*

fun PsiElement.presentText() = when (this) {
	is ZigFile -> name
	is ZigIfBlock,
	is ZigIfExprOrBlock -> "if ${children.getOrNull(1)?.text ?: ""}"
	is ZigFnDeclaration -> "fn ${fnProto.name}()"
	is ZigExternDeclaration -> "extern ${variableDeclaration?.name}"
	is ZigGlobalVarDeclaration -> "global ${variableDeclaration.name}"
	is ZigUseDeclaration -> "use ${expr.text}"
	else -> text
}

val PsiElement.treeViewTokens
	get() = this is ZigFile ||
			this is ZigFnDeclaration ||
			this is ZigExternDeclaration ||
			this is ZigGlobalVarDeclaration ||
			this is ZigUseDeclaration ||
			this is ZigIfExprOrBlock ||
			this is ZigIfErrorBlock ||
			this is ZigTestBlock ||
			this is ZigTestExprOrBlock

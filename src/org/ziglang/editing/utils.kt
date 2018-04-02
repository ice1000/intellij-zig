package org.ziglang.editing

import com.intellij.psi.PsiElement
import org.ziglang.ZigFile
import org.ziglang.psi.*

fun PsiElement.presentText() = when (this) {
//TODO something?
	is ZigFile -> name
	is ZigIfBlock,
	is ZigIfExprOrBlock -> "if ${children.getOrNull(1)?.text ?: ""}"
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
			this is ZigIfExprOrBlock ||
			this is ZigTestBlock ||
			this is ZigTestExprOrBlock

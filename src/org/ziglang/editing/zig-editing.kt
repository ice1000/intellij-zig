package org.ziglang.editing

import com.intellij.lang.*
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.psi.*
import com.intellij.psi.tree.IElementType
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import org.ziglang.*
import org.ziglang.psi.*

class ZigCommenter : Commenter {
	override fun getCommentedBlockCommentPrefix(): String? = blockCommentPrefix
	override fun getCommentedBlockCommentSuffix(): String? = blockCommentSuffix
	override fun getBlockCommentPrefix(): String? = null
	override fun getBlockCommentSuffix(): String? = null
	override fun getLineCommentPrefix() = ZIG_COMMENT_START
}

class ZigBraceMatcher : PairedBraceMatcher {
	private companion object PairHolder {
		private val PAIRS = arrayOf(
				BracePair(ZigTypes.LEFT_BRACE, ZigTypes.RIGHT_BRACE, false),
				BracePair(ZigTypes.LEFT_BRACKET, ZigTypes.RIGHT_BRACKET, false),
				BracePair(ZigTypes.LEFT_PAREN, ZigTypes.RIGHT_PAREN, false)
		)
	}

	override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset
	override fun getPairs() = PAIRS
	override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true
}

object ZigNameValidator : InputValidatorEx {
	override fun canClose(inputString: String?) = checkInput(inputString)
	override fun checkInput(inputString: String?) = inputString?.run {
		all {
			it.isLetterOrDigit() || it == '_'
		} && !firstOrNull()?.isDigit().orFalse()
	}.orFalse()

	override fun getErrorText(inputString: String?) =
			ZigBundle.message("zig.actions.new-file.invalid", inputString.orEmpty())
}

class ZigSpellcheckerStrategy : SpellcheckingStrategy() {
	override fun getTokenizer(element: PsiElement): Tokenizer<PsiElement> = when (element) {
		is PsiComment-> TEXT_TOKENIZER
		is ZigSymbol -> if (element.isDeclaration) TEXT_TOKENIZER else EMPTY_TOKENIZER
		is ZigString -> super.getTokenizer(element).takeIf { it != EMPTY_TOKENIZER } ?: TEXT_TOKENIZER
		else -> EMPTY_TOKENIZER
	}
}

class ZigFolderBuilder : FoldingBuilderEx(), DumbAware {
	private fun fold(element: PsiElement, holder: String) =
		object : FoldingDescriptor(element.node, element.textRange) {
			override fun getPlaceholderText() = holder
		}

	//树..树的遍历..?
	override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean) =
		ArrayList<FoldingDescriptor>().apply {
			root.forEach { element ->
				when (element) {
					is ZigBlock -> add(fold(element, "{...}"))
				}
			}
		}.toTypedArray()


	override fun isCollapsedByDefault(node: ASTNode) = true
	override fun getPlaceholderText(node: ASTNode) = "..."
}
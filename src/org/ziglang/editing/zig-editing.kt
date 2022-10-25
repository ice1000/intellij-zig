package org.ziglang.editing

import com.intellij.lang.*
import com.intellij.lang.cacheBuilder.DefaultWordsScanner
import com.intellij.lang.findUsages.FindUsagesProvider
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.lang.refactoring.RefactoringSupportProvider
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.psi.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import com.intellij.ui.breadcrumbs.BreadcrumbsProvider
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
		is PsiComment -> TEXT_TOKENIZER
		is ZigSymbol -> if (element.isDeclaration) TEXT_TOKENIZER else EMPTY_TOKENIZER
		is ZigString -> super.getTokenizer(element).takeIf { it != EMPTY_TOKENIZER } ?: TEXT_TOKENIZER
		else -> EMPTY_TOKENIZER
	}
}

class ZigFolderBuilder : FoldingBuilderEx(), DumbAware {
	class ZigFoldingDescriptor(element: PsiElement, private val holder: String)
		: FoldingDescriptor(element.node, element.textRange) {
		override fun getPlaceholderText() = holder
	}

	//树..树的遍历..?
	override fun buildFoldRegions(
			root: PsiElement, document: Document, quick: Boolean) = SyntaxTraverser
			.psiTraverser(root)
			.filter { it is ZigBlock || it is ZigErrorSetExpr }
			.map { ZigFoldingDescriptor(it, if (it is ZigErrorSetExpr) "error …" else "{…}") }
			.toList().toTypedArray()

	override fun isCollapsedByDefault(node: ASTNode) = true
	override fun getPlaceholderText(node: ASTNode) = "…"
}

const val TEXT_MAX = 16
const val LONG_TEXT_MAX = 24
fun cutText(it: String, textMax: Int) = if (it.length <= textMax) it else "${it.take(textMax)}…"

class ZigBreadcrumbsProvider : BreadcrumbsProvider {
	private fun ZigFnProto.text() = name?.let { "$it()" }
	override fun getLanguages() = arrayOf(ZigLanguage)
	override fun getElementInfo(element: PsiElement) = cutText(when (element) {
		is ZigFnDeclaration -> element.fnProto.text()
		is ZigExternDeclaration -> PsiTreeUtil.findChildOfType(element, ZigFnProto::class.java)?.text()
		is ZigTestDeclaration -> element.string.text
		is ZigCompTimeBlock -> "comptime"
		is ZigBlockBlock -> element.firstChild.text
		is ZigBlockExpr -> "{…}"
		is ZigGlobalVarDeclaration -> element.variableDeclaration.name
		is ZigVariableDeclaration -> element.name
		else -> null
	}.orEmpty(), TEXT_MAX)

	override fun acceptElement(element: PsiElement) = element is ZigFnDeclaration ||
			element is ZigTestDeclaration ||
			element is ZigExternDeclaration ||
			element is ZigCompTimeBlock ||
			element is ZigBlockBlock ||
			element is ZigBlockExpr ||
			element is ZigGlobalVarDeclaration ||
			element is ZigVariableDeclaration
}

class ZigFindUsagesProvider : FindUsagesProvider {
	override fun canFindUsagesFor(element: PsiElement) = element is PsiNameIdentifierOwner
	override fun getHelpId(psiElement: PsiElement): String? = null
	override fun getType(element: PsiElement) = ""
	override fun getDescriptiveName(element: PsiElement) = (element as? PsiNamedElement)?.name ?: ""
	override fun getNodeText(element: PsiElement, useFullName: Boolean) = getDescriptiveName(element)
	override fun getWordsScanner() = DefaultWordsScanner(ZigLexerAdapter(), ZigTokenType.IDENTIFIERS, ZigTokenType.COMMENTS, ZigTokenType.STRINGS)
}

class ZigRefactoringSupportProvider : RefactoringSupportProvider() {
	override fun isMemberInplaceRenameAvailable(element: PsiElement, context: PsiElement?) = true
}

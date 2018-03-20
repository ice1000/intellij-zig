package org.ziglang.editing

import com.intellij.lang.*
import com.intellij.openapi.ui.InputValidatorEx
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import org.ziglang.ZIG_COMMENT_START
import org.ziglang.ZigBundle
import org.ziglang.orFalse
import org.ziglang.psi.ZigTypes

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
		} && firstOrNull()?.isDigit()?.not().orFalse()      //starts with number was allowed?
	}.orFalse()

	override fun getErrorText(inputString: String?) =
		ZigBundle.message("zig.actions.new-file.invalid", inputString.orEmpty())


}
package org.ziglang.editing

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import org.ziglang.*
import org.ziglang.psi.ZigSymbol
import org.ziglang.psi.ZigTypes
import org.ziglang.psi.impl.prevSiblingTypeIgnoring

class ZigAnnotator : Annotator {
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when (element) {
			is ZigSymbol -> symbol(element, holder)
		}
	}

	@Suppress("RemoveRedundantBackticks", "LocalVariableName")
	private fun symbol(element: ZigSymbol, holder: AnnotationHolder) {
		when {
			element.isFunctionName ->
				holder.createInfoAnnotation(element, null)
						.textAttributes = ZigSyntaxHighlighter.FUNCTION_DECLARATION
			element.isBuiltinFunctionName -> {
				if (element.text in builtinFunctions)
					holder.createInfoAnnotation(element, null)
							.textAttributes = ZigSyntaxHighlighter.BUILTIN_FUNCTION_CALL
				else holder.createErrorAnnotation(element, ZigBundle.message("zig.lint.unknown-builtin-symbol")).run {
					highlightType = ProblemHighlightType.LIKE_UNKNOWN_SYMBOL
					@Suppress("LocalVariableName")
					val `@` = element.prevSiblingTypeIgnoring(
							ZigTypes.AT_SYM,
							ZigTokenType.LINE_COMMENT,
							TokenType.WHITE_SPACE
					)
					if (null != `@`) registerFix(ZigRemoveElementIntention(`@`,
							ZigBundle.message("zig.lint.un-builtin")))
				}
			}
		}
	}
}

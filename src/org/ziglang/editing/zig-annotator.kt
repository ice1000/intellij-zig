package org.ziglang.editing

import com.intellij.codeInspection.ProblemHighlightType
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType
import org.ziglang.*
import org.ziglang.psi.*
import org.ziglang.psi.impl.firstExprOrNull
import org.ziglang.psi.impl.prevSiblingTypeIgnoring
import java.util.regex.Pattern
import kotlin.math.min

class ZigAnnotator : Annotator {
	companion object {
		private val escapeChars = mapOf(
				'\\' to 0,
				'n' to 0,
				't' to 0,
				'x' to 2,
				'u' to 4,
				'U' to 6
		)

		private val escapeRegex = Pattern.compile("\\\\.")
	}

	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when (element) {
			is ZigSymbol -> symbol(element, holder)
			is ZigMacroExpr -> macroExpr(element, holder)
			is ZigIfBlock -> ifExpr(element.expr, holder)
			is ZigIfExprOrBlock -> ifExpr(element.firstExprOrNull(), holder)
			is ZigIfErrorBlock -> ifExpr(element.firstExprOrNull(), holder)
			is ZigIfErrorExprOrBlock -> ifExpr(element.firstExprOrNull(), holder)
			is ZigTestBlock -> ifExpr(element.firstExprOrNull(), holder)
			is ZigTestExprOrBlock -> ifExpr(element.firstExprOrNull(), holder)
			is ZigString -> string(element, holder)
		}
	}

	@Suppress("RemoveRedundantBackticks", "LocalVariableName")
	private fun macroExpr(macroExpr: ZigMacroExpr, holder: AnnotationHolder) {
		val `@` = macroExpr.firstChild?.takeIf { it.node.elementType == ZigTypes.AT_SYM } ?: return
		val element = `@`.nextSibling?.takeIf { it.node.elementType == ZigTypes.BUILTIN_FUNCTION } ?: return
		if (element.text !in builtinFunctions)
			holder.createErrorAnnotation(element, ZigBundle.message("zig.lint.unknown-builtin-symbol")).apply {
				highlightType = ProblemHighlightType.LIKE_UNKNOWN_SYMBOL
				registerFix(ZigRemoveElementIntention(`@`,
						ZigBundle.message("zig.lint.un-builtin")))
			}
	}

	private fun symbol(element: ZigSymbol, holder: AnnotationHolder) {
		when {
			element.isFunctionName ->
				holder.createInfoAnnotation(element, null)
						.textAttributes = ZigSyntaxHighlighter.FUNCTION_DECLARATION
		}
	}

	private fun ifExpr(condition: ZigExpr?, holder: AnnotationHolder) {
		when {
			condition is ZigBoolean ->
				holder.createWarningAnnotation(condition, ZigBundle.message("zig.lint.const-condition", condition.text))
		}
	}

	private fun string(element: ZigString, holder: AnnotationHolder) {
		fun String.nextString(start: Int, length: Int) =
				substring(start, min(start + length, this.length))

		val matcher = escapeRegex.matcher(element.text)

		while (matcher.find()) {
			val start = matcher.start()
			val end = matcher.end()
			val char = matcher.group()[1]

			if (char in escapeChars) {
				val nextCount = escapeChars[char] ?: return
				val accept = element.text.nextString(start + 2, nextCount).run {
					isEmpty() or all { it in "0123456789ABCDEFabcdef" }
				}

				val range = element.textRange.subRange(start, end + nextCount - 1)
				if (accept) {
					holder.createInfoAnnotation(range, "Hello world!")
							.textAttributes = ZigSyntaxHighlighter.STRING_ESCAPE
					continue
				} else holder.createErrorAnnotation(range, "Goodbye world...")
						.textAttributes = ZigSyntaxHighlighter.STRING_ESCAPE_INVALID
			}
		}
	}
}
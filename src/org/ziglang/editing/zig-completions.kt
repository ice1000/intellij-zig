package org.ziglang.editing

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns
import com.intellij.util.ProcessingContext
import icons.ZigIcons
import org.ziglang.ZigBundle
import org.ziglang.psi.ZigSymbol

class ZigCompletionProvider(private val list: List<LookupElement>)
	: CompletionProvider<CompletionParameters>() {
	override fun addCompletions(
			parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) =
			list.forEach(result::addElement)
}

class ZigCompletionContributor : CompletionContributor() {
	private companion object CompletionHolder {
		private val KEYWORD_LITERALS = listOf(
				"unreachable",
				"undefined",
				"error",
				"this",
				"suspend",
				"null",
				"true",
				"false"
		).map {
			LookupElementBuilder.create(it)
					.withIcon(ZigIcons.ZIG_BIG_ICON)
					.withTypeText(ZigBundle.message("zig.completions.keywords"))
					.bold()
		}
	}

	init {
		extend(CompletionType.BASIC,
				PlatformPatterns.psiElement(ZigSymbol::class.java),
				ZigCompletionProvider(KEYWORD_LITERALS))
	}
}


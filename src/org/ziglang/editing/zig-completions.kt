package org.ziglang.editing

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext
import icons.ZigIcons
import org.ziglang.ZigBundle
import org.ziglang.builtinFunctions
import org.ziglang.psi.ZigTypes

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
		private val BUILTIN_FUNCTIONS = builtinFunctions.map {
			LookupElementBuilder.create(it)
					.withIcon(ZigIcons.ZIG_BIG_ICON)
					.withTypeText(ZigBundle.message("zig.completions.built-in"))
					.withItemTextUnderlined(true)
		}
	}

	override fun invokeAutoPopup(position: PsiElement, typeChar: Char): Boolean {
		return typeChar in "@." || super.invokeAutoPopup(position, typeChar)
	}

	init {
		extend(CompletionType.BASIC,
				psiElement(ZigTypes.SYM)
						.andNot(psiElement().afterLeaf(".")),
				ZigCompletionProvider(KEYWORD_LITERALS))
		extend(CompletionType.BASIC,
				psiElement(ZigTypes.SYM)
						.afterLeaf("@")
						.andNot(psiElement().afterLeaf(".")),
				ZigCompletionProvider(BUILTIN_FUNCTIONS))
	}
}


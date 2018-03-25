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
				"packed",
				"null",
				"true",
				"false"
		).map {
			LookupElementBuilder.create(it)
					.withIcon(ZigIcons.ZIG_BIG_ICON)
					.withTypeText(ZigBundle.message("zig.completions.keywords"))
					.bold()
		}
		private val EXPR_KEYWORDS = listOf(
				"for",
				"while",
				"if",
				"switch",
				"union",
				"struct",
				"enum",
				"return",
				"try"
		).map {
			LookupElementBuilder.create("$it ")
					.withPresentableText(it)
					.withIcon(ZigIcons.ZIG_BIG_ICON)
					.withTypeText(ZigBundle.message("zig.completions.keywords"))
					.bold()
		}
		private val TOP_KEYWORDS = listOf(
				"const",
				"var",
				"fn",
				"comptime",
				"inline",
				"export",
				"pub"
		).map {
			LookupElementBuilder.create("$it ")
					.withPresentableText(it)
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
		return typeChar in "@.(" || super.invokeAutoPopup(position, typeChar)
	}

	init {
		extend(CompletionType.BASIC,
				psiElement(ZigTypes.SYM)
						.andNot(psiElement().afterLeaf(".", "@")),
				ZigCompletionProvider(KEYWORD_LITERALS))
		extend(CompletionType.BASIC,
				psiElement(ZigTypes.SYM)
						.andNot(psiElement().afterLeaf(".", "@"))
						.andNot(psiElement()
								.beforeLeaf(psiElement(ZigTypes.SEMICOLON_SYM))),
				ZigCompletionProvider(TOP_KEYWORDS))
		extend(CompletionType.BASIC,
				psiElement(ZigTypes.SYM)
						.andNot(psiElement().afterLeaf(".", "@")),
				ZigCompletionProvider(EXPR_KEYWORDS))
		extend(CompletionType.BASIC,
				psiElement()
						.afterLeaf("@")
						.andNot(psiElement().afterLeaf(".")),
				ZigCompletionProvider(BUILTIN_FUNCTIONS))
	}
}


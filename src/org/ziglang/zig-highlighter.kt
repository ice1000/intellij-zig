package org.ziglang

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.tree.IElementType
import org.ziglang.psi.ZigTypes

object ZigSyntaxHighlighter : SyntaxHighlighter {
	@JvmField val KEYWORDS = listOf(
			ZigTypes.TEST_KEYWORD,
			ZigTypes.PUB_KEYWORD,
			ZigTypes.EXPORT_KEYWORD,
			ZigTypes.COMPTIME_KEYWORD,
			ZigTypes.CONST_KEYWORD,
			ZigTypes.VAR_KEYWORD,
			ZigTypes.ALIGN_KEYWORD,
			ZigTypes.SECTION_KEYWORD,
			ZigTypes.USE_KEYWORD,
			ZigTypes.EXTERN_KEYWORD,
			ZigTypes.NAKEDCC_KEYWORD,
			ZigTypes.STDCALLCC_KEYWORD,
			ZigTypes.ASYNC_KEYWORD,
			ZigTypes.FN_KEYWORD,
			ZigTypes.SECTION_KEYWORD,
			ZigTypes.ALIGN_KEYWORD,
			ZigTypes.INLINE_KEYWORD,
			ZigTypes.COMPTIME_KEYWORD,
			ZigTypes.NOALIAS_KEYWORD,
			ZigTypes.ASM_KEYWORD,
			ZigTypes.VOLATILE_KEYWORD,
			ZigTypes.RETURN_KEYWORD,
			ZigTypes.TRY_KEYWORD,
			ZigTypes.AWAIT_KEYWORD,
			ZigTypes.BREAK_KEYWORD,
			ZigTypes.CANCEL_KEYWORD,
			ZigTypes.RESUME_KEYWORD,
			ZigTypes.CATCH_KEYWORD,
			ZigTypes.DEFER_KEYWORD,
			ZigTypes.DEFERROR_KEYWORD,
			ZigTypes.TRUE_KEYWORD,
			ZigTypes.FALSE_KEYWORD,
			ZigTypes.NULL_KEYWORD,
			ZigTypes.ENUM_KEYWORD,
			ZigTypes.CONTINUE_KEYWORD,
			ZigTypes.AND_KEYWORD,
			ZigTypes.IF_KEYWORD,
			ZigTypes.ELSE_KEYWORD,
			ZigTypes.WHILE_KEYWORD,
			ZigTypes.SUSPEND_KEYWORD,
			ZigTypes.THIS_KEYWORD,
			ZigTypes.ERROR_KEYWORD,
			ZigTypes.UNDEFINED_KEYWORD,
			ZigTypes.UNREACHABLE_KEYWORD,
			ZigTypes.PACKED_KEYWORD,
			ZigTypes.STRUCT_KEYWORD,
			ZigTypes.UNION_KEYWORD
	)

	@JvmField val KEYWORD = TextAttributesKey.createTextAttributesKey("ZIG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
	@JvmField val SYMBOL = TextAttributesKey.createTextAttributesKey("ZIG_SYMBOL", HighlighterColors.TEXT)
	@JvmField val NUMBER = TextAttributesKey.createTextAttributesKey("ZIG_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
	@JvmField val FLOAT_LIT = TextAttributesKey.createTextAttributesKey("ZIG_FLOAT_LIT", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
	@JvmField val STRING = TextAttributesKey.createTextAttributesKey("ZIG_STRING", DefaultLanguageHighlighterColors.STRING)

	@JvmField val KEYWORD_KEY = arrayOf(KEYWORD)
	@JvmField val STRING_KEY = arrayOf(STRING)
	@JvmField val NUMBER_KEY = arrayOf(NUMBER)

	override fun getHighlightingLexer() = ZigLexerAdapter()
	override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
		ZigTypes.STR -> STRING_KEY
		ZigTypes.INT_LITERAL,
		ZigTypes.FLOAT_LITERAL -> NUMBER_KEY
		in KEYWORDS -> KEYWORD_KEY
		else -> emptyArray()
	}
}

class ZigSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) =
			ZigSyntaxHighlighter
}



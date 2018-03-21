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
	@JvmField val KEYWORDS = arrayOf(
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
			ZigTypes.UNREACHABLE_KEYWORD,
			ZigTypes.PACKED_KEYWORD,
			ZigTypes.STRUCT_KEYWORD,
			ZigTypes.UNION_KEYWORD,
			ZigTypes.UNDEFINED_KEYWORD
	)

	@JvmField val OPERATORS = arrayOf(
			ZigTypes.COMMA_SYM,
			ZigTypes.SEMICOLON_SYM,
			ZigTypes.COLON_SYM,
			ZigTypes.LEFT_PAREN,
			ZigTypes.RIGHT_PAREN,
			ZigTypes.LEFT_BRACE,
			ZigTypes.RIGHT_BRACE,
			ZigTypes.LEFT_BRACKET,
			ZigTypes.RIGHT_BRACKET,
			ZigTypes.RANGE_SYM,
			ZigTypes.SLICE_SYM,
			ZigTypes.DOT_SYM,
			ZigTypes.GT_SYM,
			ZigTypes.LT_SYM,
			ZigTypes.PLUS_SYM,
			ZigTypes.MINUS_SYM,
			ZigTypes.STAR_SYM,
			ZigTypes.DIV_SYM,
			ZigTypes.EXPONENT_SYM,
			ZigTypes.SEP_SYM,
			ZigTypes.NOT_SYM,
			ZigTypes.VERY_QUESTION_SYM,
			ZigTypes.QUESTION_SYM,
			ZigTypes.BITWISE_NOT_SYM,
			ZigTypes.AND_SYM,
			ZigTypes.MOD_SYM,
			ZigTypes.EQ_SYM,
			ZigTypes.AT_SYM,
			ZigTypes.PLUS_MOD_SYM,
			ZigTypes.MINUS_MOD_SYM,
			ZigTypes.PLUS_ASSIGN_SYM,
			ZigTypes.MINUS_ASSIGN_SYM,
			ZigTypes.STAR_ASSIGN_SYM,
			ZigTypes.DIV_ASSIGN_SYM,
			ZigTypes.EXPONENT_ASSIGN_SYM,
			ZigTypes.OR_ASSIGN_SYM,
			ZigTypes.AND_ASSIGN_SYM,
			ZigTypes.MOD_ASSIGN_SYM,
			ZigTypes.STAR_STAR_SYM,
			ZigTypes.SEP_SEP_SYM,
			ZigTypes.STAR_SYM,
			ZigTypes.SHL_SYM,
			ZigTypes.SHR_SYM,
			ZigTypes.UNEQUAL_SYM,
			ZigTypes.GE_SYM,
			ZigTypes.LE_SYM,
			ZigTypes.EQUAL_SYM,
			ZigTypes.INC_SYM,
			ZigTypes.PLUS_MOD_ASSIGN_SYM,
			ZigTypes.MINUS_MOD_ASSIGN_SYM,
			ZigTypes.STAR_MOD_ASSIGN_SYM,
			ZigTypes.SHL_ASSIGN_SYM,
			ZigTypes.SHR_ASSIGN_SYM,
			ZigTypes.ARROW_SYM,
			ZigTypes.SMALL_ARROW_SYM
	)

	@JvmField val KEYWORD = TextAttributesKey.createTextAttributesKey("ZIG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
	@JvmField val SYMBOL = TextAttributesKey.createTextAttributesKey("ZIG_SYMBOL", HighlighterColors.TEXT)
	@JvmField val NUMBER = TextAttributesKey.createTextAttributesKey("ZIG_NUMBER", DefaultLanguageHighlighterColors.NUMBER)
	@JvmField val FLOAT_LIT = TextAttributesKey.createTextAttributesKey("ZIG_FLOAT_LIT", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
	@JvmField val STRING = TextAttributesKey.createTextAttributesKey("ZIG_STRING", DefaultLanguageHighlighterColors.STRING)
	@JvmField val LINE_COMMENT = TextAttributesKey.createTextAttributesKey("ZIG_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
	@JvmField val SEMICOLON = TextAttributesKey.createTextAttributesKey("ZIG_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)
	@JvmField val UNDEFINED = TextAttributesKey.createTextAttributesKey("ZIG_UNDEFINED", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)
	@JvmField val OPERATOR = TextAttributesKey.createTextAttributesKey("ZIG_OPERATORS", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)

	@JvmField val KEYWORD_KEY = arrayOf(KEYWORD)
	@JvmField val STRING_KEY = arrayOf(STRING)
	@JvmField val NUMBER_KEY = arrayOf(NUMBER)
	@JvmField val SYMBOL_KEY = arrayOf(SYMBOL)
	@JvmField val LINE_COMMENT_KEY = arrayOf(LINE_COMMENT)
	@JvmField val SEMICOLON_KEY = arrayOf(SEMICOLON)
	@JvmField val UNDEFINED_KEY = arrayOf(UNDEFINED)
	@JvmField val OPERATOR_KEY = arrayOf(OPERATOR)

	override fun getHighlightingLexer() = ZigLexerAdapter()
	override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
		ZigTypes.SYM -> SYMBOL_KEY
		ZigTokenType.LINE_COMMENT -> LINE_COMMENT_KEY
		ZigTypes.SEMICOLON_SYM -> SEMICOLON_KEY
		ZigTypes.STR -> STRING_KEY
		ZigTypes.INT_LITERAL,
		ZigTypes.FLOAT_LITERAL -> NUMBER_KEY
		ZigTypes.UNDEFINED_KEYWORD -> UNDEFINED_KEY
		in KEYWORDS -> KEYWORD_KEY
		in OPERATORS -> OPERATOR_KEY
		else -> emptyArray()
	}
}

class ZigSyntaxHighlighterFactory : SyntaxHighlighterFactory() {
	override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?) =
			ZigSyntaxHighlighter
}



package org.ziglang.highlight

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.HighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.psi.tree.IElementType
import org.ziglang.ZigLexerAdapter
import org.ziglang.ZigTokenType
import org.ziglang.psi.ZigTypes.*

object ZigSyntaxHighlighter : SyntaxHighlighter {
    @JvmField
    val KEYWORDS = arrayOf(
        TEST_KEYWORD,
        PUB_KEYWORD,
        EXPORT_KEYWORD,
        COMPTIME_KEYWORD,
        CONST_KEYWORD,
        VAR_KEYWORD,
        ALIGN_KEYWORD,
        SECTION_KEYWORD,
        USE_KEYWORD,
        EXTERN_KEYWORD,
        NAKEDCC_KEYWORD,
        STDCALLCC_KEYWORD,
        ORELSE_KEYWORD,
        ASYNC_KEYWORD,
        FN_KEYWORD,
        SECTION_KEYWORD,
        ALIGN_KEYWORD,
        INLINE_KEYWORD,
        COMPTIME_KEYWORD,
        NOALIAS_KEYWORD,
        ASM_KEYWORD,
        VOLATILE_KEYWORD,
        RETURN_KEYWORD,
        TRY_KEYWORD,
        AWAIT_KEYWORD,
        BREAK_KEYWORD,
        CANCEL_KEYWORD,
        RESUME_KEYWORD,
        CATCH_KEYWORD,
        DEFER_KEYWORD,
        DEFERROR_KEYWORD,
        TRUE_KEYWORD,
        FALSE_KEYWORD,
        NULL_KEYWORD,
        ENUM_KEYWORD,
        CONTINUE_KEYWORD,
        AND_KEYWORD,
        OR_KEYWORD,
        IF_KEYWORD,
        FOR_KEYWORD,
        ELSE_KEYWORD,
        WHILE_KEYWORD,
        SUSPEND_KEYWORD,
        THIS_KEYWORD,
        ERROR_KEYWORD,
        UNREACHABLE_KEYWORD,
        PACKED_KEYWORD,
        STRUCT_KEYWORD,
        UNION_KEYWORD,
        SWITCH_KEYWORD,
        UNDEFINED_KEYWORD
    )

    @JvmField
    val PARENS = arrayOf(
        LEFT_PAREN,
        RIGHT_PAREN
    )

    @JvmField
    val BRACKETS = arrayOf(
        LEFT_BRACKET,
        RIGHT_BRACKET
    )

    @JvmField
    val BRACES = arrayOf(
        LEFT_BRACE,
        RIGHT_BRACE
    )

    @JvmField
    val OPERATORS = arrayOf(
        COMMA_SYM,
        COLON_SYM,
        RANGE_SYM,
        SLICE_SYM,
        DOT_SYM,
        GT_SYM,
        LT_SYM,
        PLUS_SYM,
        MINUS_SYM,
        STAR_SYM,
        DIV_SYM,
        EXPONENT_SYM,
        SEP_SYM,
        NOT_SYM,
        VERY_QUESTION_SYM,
        QUESTION_SYM,
        BITWISE_NOT_SYM,
        AND_SYM,
        MOD_SYM,
        EQ_SYM,
        AT_SYM,
        PLUS_MOD_SYM,
        MINUS_MOD_SYM,
        PLUS_ASSIGN_SYM,
        MINUS_ASSIGN_SYM,
        STAR_ASSIGN_SYM,
        DIV_ASSIGN_SYM,
        EXPONENT_ASSIGN_SYM,
        OR_ASSIGN_SYM,
        AND_ASSIGN_SYM,
        MOD_ASSIGN_SYM,
        STAR_STAR_SYM,
        SEP_SEP_SYM,
        STAR_SYM,
        SHL_SYM,
        SHR_SYM,
        UNEQUAL_SYM,
        GE_SYM,
        LE_SYM,
        EQUAL_SYM,
        INC_SYM,
        PLUS_MOD_ASSIGN_SYM,
        MINUS_MOD_ASSIGN_SYM,
        STAR_MOD_ASSIGN_SYM,
        SHL_ASSIGN_SYM,
        SHR_ASSIGN_SYM,
        ARROW_SYM,
        SMALL_ARROW_SYM
    )

    @JvmField
    val KEYWORD = TextAttributesKey.createTextAttributesKey("ZIG_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)

    @JvmField
    val SYMBOL = TextAttributesKey.createTextAttributesKey("ZIG_SYMBOL", HighlighterColors.TEXT)

    @JvmField
    val NUMBER = TextAttributesKey.createTextAttributesKey("ZIG_NUMBER", DefaultLanguageHighlighterColors.NUMBER)

    @JvmField
    val STRING = TextAttributesKey.createTextAttributesKey("ZIG_STRING", DefaultLanguageHighlighterColors.STRING)

    @JvmField
    val STRING_ESCAPE = TextAttributesKey.createTextAttributesKey(
        "ZIG_STRING_ESCAPE",
        DefaultLanguageHighlighterColors.VALID_STRING_ESCAPE
    )

    @JvmField
    val STRING_ESCAPE_INVALID = TextAttributesKey.createTextAttributesKey(
        "ZIG_STRING_ESCAPE_INVALID",
        DefaultLanguageHighlighterColors.INVALID_STRING_ESCAPE
    )

    @JvmField
    val LINE_COMMENT =
        TextAttributesKey.createTextAttributesKey("ZIG_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)

    @JvmField
    val SEMICOLON =
        TextAttributesKey.createTextAttributesKey("ZIG_SEMICOLON", DefaultLanguageHighlighterColors.SEMICOLON)

    @JvmField
    val UNDEFINED =
        TextAttributesKey.createTextAttributesKey("ZIG_UNDEFINED", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)

    @JvmField
    val OPERATOR =
        TextAttributesKey.createTextAttributesKey("ZIG_OPERATORS", DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL)

    @JvmField
    val PAREN = TextAttributesKey.createTextAttributesKey("ZIG_PARENS", DefaultLanguageHighlighterColors.PARENTHESES)

    @JvmField
    val BRACKET = TextAttributesKey.createTextAttributesKey("ZIG_BRACKETS", DefaultLanguageHighlighterColors.BRACKETS)

    @JvmField
    val BRACE = TextAttributesKey.createTextAttributesKey("ZIG_BRACES", DefaultLanguageHighlighterColors.BRACES)

    @JvmField
    val FUNCTION_DECLARATION = TextAttributesKey.createTextAttributesKey(
        "ZIG_FUNCTION_DECLARATION",
        DefaultLanguageHighlighterColors.FUNCTION_DECLARATION
    )

    @JvmField
    val BUILTIN_FUNCTION_CALL = TextAttributesKey.createTextAttributesKey(
        "ZIG_BUILTIN_FUNCTION_CALL",
        DefaultLanguageHighlighterColors.PREDEFINED_SYMBOL
    )

    @JvmField
    val KEYWORD_KEY = arrayOf(KEYWORD)

    @JvmField
    val STRING_KEY = arrayOf(STRING)

    @JvmField
    val NUMBER_KEY = arrayOf(NUMBER)

    @JvmField
    val SYMBOL_KEY = arrayOf(SYMBOL)

    @JvmField
    val LINE_COMMENT_KEY = arrayOf(LINE_COMMENT)

    @JvmField
    val SEMICOLON_KEY = arrayOf(SEMICOLON)

    @JvmField
    val UNDEFINED_KEY = arrayOf(UNDEFINED)

    @JvmField
    val OPERATOR_KEY = arrayOf(OPERATOR)

    @JvmField
    val PARENS_KEY = arrayOf(PAREN)

    @JvmField
    val BRACKETS_KEY = arrayOf(BRACKET)

    @JvmField
    val BRACES_KEY = arrayOf(BRACE)

    @JvmField
    val BUILTIN_FUNCTION_CALL_KEY = arrayOf(BUILTIN_FUNCTION_CALL)

    override fun getHighlightingLexer() = ZigLexerAdapter()
    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> = when (tokenType) {
        SYM -> SYMBOL_KEY
        ZigTokenType.LINE_COMMENT -> LINE_COMMENT_KEY
        SEMICOLON_SYM -> SEMICOLON_KEY
        STR -> STRING_KEY
        INT_LITERAL,
        FLOAT_LITERAL -> NUMBER_KEY
        UNDEFINED_KEYWORD -> UNDEFINED_KEY
        BUILTIN_FUNCTION -> BUILTIN_FUNCTION_CALL_KEY
        in PARENS -> PARENS_KEY
        in BRACKETS -> BRACKETS_KEY
        in BRACES -> BRACES_KEY
        in KEYWORDS -> KEYWORD_KEY
        in OPERATORS -> OPERATOR_KEY
        else -> emptyArray()
    }
}


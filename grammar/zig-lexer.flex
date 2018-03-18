package org.ziglang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.ziglang.psi.ZigTypes;

%%

%{
  public ZigLexer() { this((java.io.Reader) null); }
%}

%class ZigLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{ return;
%eof}

OTHERWISE=[^]

%%

{OTHERWISE} { return TokenType.BAD_CHARACTER; }

, { return ZigTypes.COMMA_SYM; }

TEST_KEYWORD
PUB_KEYWORD
EXPORT_KEYWORD
COMPTIME_KEYWORD
CONST_KEYWORD
VAR_KEYWORD
ALIGN_KEYWORD
SECTION_KEYWORD
USE_KEYWORD
EXTERN_KEYWORD
NAKEDCC_KEYWORD
STDCALLCC_KEYWORD
ASYNC_KEYWORD
FN_KEYWORD
SECTION_KEYWORD
ALIGN_KEYWORD
INLINE_KEYWORD
COMPTIME_KEYWORD
NOALIAS_KEYWORD
ASM_KEYWORD
VOLATILE_KEYWORD
RETURN_KEYWORD
TRY_KEYWORD
AWAIT_KEYWORD
BREAK_KEYWORD
CANCEL_KEYWORD
RESUME_KEYWORD
CATCH_KEYWORD
DEFER_KEYWORD
DEFERROR_KEYWORD
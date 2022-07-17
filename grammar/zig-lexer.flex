package org.ziglang;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;
import org.ziglang.psi.ZigTypes;
import org.ziglang.ZigTokenType;

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
WHITESPACE=[\s\n\f\r\t]+
SEMICOLON=;
COMMENT=\/\/[^\n]*

NUM_SUFFIX=-?\d+
P_SUFFIX=[pP]{NUM_SUFFIX}
E_SUFFIX=[eE]{NUM_SUFFIX}
HEXDIGIT=[a-fA-F0-9]
HEX_NUM=0[xX]{HEXDIGIT}+({P_SUFFIX}|{E_SUFFIX})?
OCT_NUM=0[oO][0-7]+
BIN_NUM=0[bB][01]+
DEC_NUM=\d+{E_SUFFIX}?
INTEGER={HEX_NUM}|{OCT_NUM}|{BIN_NUM}|{DEC_NUM}
FLOAT=((\d+\.\d*)|(\d*\.\d+))({E_SUFFIX}|{P_SUFFIX})?

SYMBOL_CHAR=[a-zA-Z_] // TODO
SYMBOL={SYMBOL_CHAR}({SYMBOL_CHAR}|\d)*

INCOMPLETE_STRING=c?\"([^\"\\\n]|\\[^])*
STRING_LITERAL={INCOMPLETE_STRING}\"

INCOMPLETE_CHAR='([^'\\\n]|\\[^])*
CHAR_LITERAL={INCOMPLETE_CHAR}'

%state AFTER_AT

%%

{COMMENT} { return ZigTokenType.LINE_COMMENT; }
{WHITESPACE} { return TokenType.WHITE_SPACE; }

<AFTER_AT> {SYMBOL} { yybegin(YYINITIAL); return ZigTypes.BUILTIN_FUNCTION; }
<AFTER_AT> [^] { yybegin(YYINITIAL); return TokenType.BAD_CHARACTER; }

\<\<= { return ZigTypes.SHL_ASSIGN_SYM; } // <<=
>>= { return ZigTypes.SHR_ASSIGN_SYM; }
=> { return ZigTypes.ARROW_SYM; }
-> { return ZigTypes.SMALL_ARROW_SYM; }

\+%= { return ZigTypes.PLUS_MOD_ASSIGN_SYM; }
\-%= { return ZigTypes.MINUS_MOD_ASSIGN_SYM; }
\*%= { return ZigTypes.STAR_MOD_ASSIGN_SYM; }
//\/%= { return ZigTypes.DIV_MOD_ASSIGN_SYM; }

\+% { return ZigTypes.PLUS_MOD_SYM; }
\-% { return ZigTypes.MINUS_MOD_SYM; }
\*% { return ZigTypes.STAR_SYM; }

\<< { return ZigTypes.SHL_SYM; }
\>> { return ZigTypes.SHR_SYM; }

\+= { return ZigTypes.PLUS_ASSIGN_SYM; }
\-= { return ZigTypes.MINUS_ASSIGN_SYM; }
\*= { return ZigTypes.STAR_ASSIGN_SYM; }
\/= { return ZigTypes.DIV_ASSIGN_SYM; }
\^= { return ZigTypes.EXPONENT_ASSIGN_SYM; }
\|= { return ZigTypes.OR_ASSIGN_SYM; }
\!= { return ZigTypes.UNEQUAL_SYM; }
\>= { return ZigTypes.GE_SYM; }
\<= { return ZigTypes.LE_SYM; }
&= { return ZigTypes.AND_ASSIGN_SYM; }
%= { return ZigTypes.MOD_ASSIGN_SYM; }
== { return ZigTypes.EQUAL_SYM; }

\+\+ { return ZigTypes.INC_SYM; }
// \-\- { return ZigTypes.DEC_SYM; }
\*\* { return ZigTypes.STAR_STAR_SYM; }
\|\| { return ZigTypes.SEP_SEP_SYM; }

\> { return ZigTypes.GT_SYM; }
\< { return ZigTypes.LT_SYM; }
\+ { return ZigTypes.PLUS_SYM; }
\- { return ZigTypes.MINUS_SYM; }
\* { return ZigTypes.STAR_SYM; }
\/ { return ZigTypes.DIV_SYM; }
\^ { return ZigTypes.EXPONENT_SYM; }
\| { return ZigTypes.SEP_SYM; }
\! { return ZigTypes.NOT_SYM; }
\?\? { return ZigTypes.VERY_QUESTION_SYM; } // 敲皮
\? { return ZigTypes.QUESTION_SYM; }
\~ { return ZigTypes.BITWISE_NOT_SYM; }
& { return ZigTypes.AND_SYM; }
% { return ZigTypes.MOD_SYM; }
= { return ZigTypes.EQ_SYM; }
@ { yybegin(AFTER_AT); return ZigTypes.AT_SYM; }

, { return ZigTypes.COMMA_SYM; }
{SEMICOLON} { return ZigTypes.SEMICOLON_SYM; }
: { return ZigTypes.COLON_SYM; }
\( { return ZigTypes.LEFT_PAREN; }
\) { return ZigTypes.RIGHT_PAREN; }
\{ { return ZigTypes.LEFT_BRACE; }
\} { return ZigTypes.RIGHT_BRACE; }
\[ { return ZigTypes.LEFT_BRACKET; }
\] { return ZigTypes.RIGHT_BRACKET; }
_ { return ZigTypes.UNDERSCORE_SYM; }
\* { return ZigTypes.ASTERISK_SYM; }

\.\.\. { return ZigTypes.RANGE_SYM; }
\.\. { return ZigTypes.SLICE_SYM; }
\. { return ZigTypes.DOT_SYM; }

{STRING_LITERAL} { return ZigTypes.STR; }
{INCOMPLETE_STRING} { return TokenType.BAD_CHARACTER; }
{CHAR_LITERAL} { return ZigTypes.CHAR_LITERAL; }
{INCOMPLETE_CHAR} { return TokenType.BAD_CHARACTER; }

test { return ZigTypes.TEST_KEYWORD; }
pub { return ZigTypes.PUB_KEYWORD; }
export { return ZigTypes.EXPORT_KEYWORD; }
switch { return ZigTypes.SWITCH_KEYWORD; }
comptime { return ZigTypes.COMPTIME_KEYWORD; }
const { return ZigTypes.CONST_KEYWORD; }
var { return ZigTypes.VAR_KEYWORD; }
align { return ZigTypes.ALIGN_KEYWORD; }
for { return ZigTypes.FOR_KEYWORD; }
section { return ZigTypes.SECTION_KEYWORD; }
use { return ZigTypes.USE_KEYWORD; }
extern { return ZigTypes.EXTERN_KEYWORD; }
nakedcc { return ZigTypes.NAKEDCC_KEYWORD; }
stdcallcc { return ZigTypes.STDCALLCC_KEYWORD; }
orelse { return ZigTypes.ORELSE_KEYWORD; }
async { return ZigTypes.ASYNC_KEYWORD; }
fn { return ZigTypes.FN_KEYWORD; }
section { return ZigTypes.SECTION_KEYWORD; }
align { return ZigTypes.ALIGN_KEYWORD; }
inline { return ZigTypes.INLINE_KEYWORD; }
comptime { return ZigTypes.COMPTIME_KEYWORD; }
noalias { return ZigTypes.NOALIAS_KEYWORD; }
asm { return ZigTypes.ASM_KEYWORD; }
volatile { return ZigTypes.VOLATILE_KEYWORD; }
return { return ZigTypes.RETURN_KEYWORD; }
try { return ZigTypes.TRY_KEYWORD; }
await { return ZigTypes.AWAIT_KEYWORD; }
break { return ZigTypes.BREAK_KEYWORD; }
cancel { return ZigTypes.CANCEL_KEYWORD; }
resume { return ZigTypes.RESUME_KEYWORD; }
catch { return ZigTypes.CATCH_KEYWORD; }
defer { return ZigTypes.DEFER_KEYWORD; }
errdefer { return ZigTypes.DEFERROR_KEYWORD; }
true { return ZigTypes.TRUE_KEYWORD; }
false { return ZigTypes.FALSE_KEYWORD; }
null { return ZigTypes.NULL_KEYWORD; }
enum { return ZigTypes.ENUM_KEYWORD; }
continue { return ZigTypes.CONTINUE_KEYWORD; }
and { return ZigTypes.AND_KEYWORD; }
or { return ZigTypes.OR_KEYWORD; }
if { return ZigTypes.IF_KEYWORD; }
else { return ZigTypes.ELSE_KEYWORD; }
while { return ZigTypes.WHILE_KEYWORD; }
suspend { return ZigTypes.SUSPEND_KEYWORD; }
this { return ZigTypes.THIS_KEYWORD; }
error { return ZigTypes.ERROR_KEYWORD; }
undefined { return ZigTypes.UNDEFINED_KEYWORD; }
unreachable { return ZigTypes.UNREACHABLE_KEYWORD; }
packed { return ZigTypes.PACKED_KEYWORD; }
struct { return ZigTypes.STRUCT_KEYWORD; }
union { return ZigTypes.UNION_KEYWORD; }

{SYMBOL} { return ZigTypes.SYM; }
{INTEGER} { return ZigTypes.INT_LITERAL; }
{FLOAT} { return ZigTypes.FLOAT_LITERAL; }

{OTHERWISE} { return TokenType.BAD_CHARACTER; }

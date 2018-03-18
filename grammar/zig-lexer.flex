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

test { return ZigTypes.TEST_KEYWORD; }
pub { return ZigTypes.PUB_KEYWORD; }
export { return ZigTypes.EXPORT_KEYWORD; }
comptime { return ZigTypes.COMPTIME_KEYWORD; }
const { return ZigTypes.CONST_KEYWORD; }
var { return ZigTypes.VAR_KEYWORD; }
align { return ZigTypes.ALIGN_KEYWORD; }
section { return ZigTypes.SECTION_KEYWORD; }
use { return ZigTypes.USE_KEYWORD; }
extern { return ZigTypes.EXTERN_KEYWORD; }
nakedcc { return ZigTypes.NAKEDCC_KEYWORD; }
stdcallcc { return ZigTypes.STDCALLCC_KEYWORD; }
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
deferror { return ZigTypes.DEFERROR_KEYWORD; }
true { return ZigTypes.TRUE_KEYWORD; }
false { return ZigTypes.FALSE_KEYWORD; }
null { return ZigTypes.NULL_KEYWORD; }
enum { return ZigTypes.ENUM_KEYWORD; }
continue { return ZigTypes.CONTINUE_KEYWORD; }
and { return ZigTypes.AND_KEYWORD }
if { return ZigTypes.IF_KEYWORD }
else { return ZigTypes.ELSE_KEYWORD }
while { return ZigTypes.WHILE_KEYWORD }
suspend { return ZigTypes.SUSPEND_KEYWORD }
this { return ZigTypes.THIS_KEYWORD }
error { return ZigTypes.ERROR_KEYWORD }
undefined { return ZigTypes.UNDEFINED_KEYWORD }
unreachable { return ZigTypes.UNREACHABLE_KEYWORD }
packed { return ZigTypes.PACKED_KEYWORD }
struct { return ZigTypes.STRUCT_KEYWORD }
union { return ZigTypes.UNION_KEYWORD }

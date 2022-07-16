// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import org.ziglang.ZigElementType;
import org.ziglang.ZigTokenType;
import org.ziglang.psi.impl.*;

public interface ZigTypes {

  IElementType ADDITION_EXPR = new ZigElementType("ADDITION_EXPR");
  IElementType ADDITION_OPERATOR = new ZigElementType("ADDITION_OPERATOR");
  IElementType ARRAY_ACCESS_SUFFIX = new ZigElementType("ARRAY_ACCESS_SUFFIX");
  IElementType ARRAY_EXPR = new ZigElementType("ARRAY_EXPR");
  IElementType ASM_CLOBBERS = new ZigElementType("ASM_CLOBBERS");
  IElementType ASM_EXPR = new ZigElementType("ASM_EXPR");
  IElementType ASM_INPUT = new ZigElementType("ASM_INPUT");
  IElementType ASM_INPUT_ITEM = new ZigElementType("ASM_INPUT_ITEM");
  IElementType ASM_OUTPUT = new ZigElementType("ASM_OUTPUT");
  IElementType ASM_OUTPUT_ITEM = new ZigElementType("ASM_OUTPUT_ITEM");
  IElementType ASSIGN_EXPR = new ZigElementType("ASSIGN_EXPR");
  IElementType ASSIGN_OPERATOR = new ZigElementType("ASSIGN_OPERATOR");
  IElementType ASYNC_EXPR = new ZigElementType("ASYNC_EXPR");
  IElementType AWAIT_EXPR = new ZigElementType("AWAIT_EXPR");
  IElementType BINARY_AND_EXPR = new ZigElementType("BINARY_AND_EXPR");
  IElementType BINARY_OR_EXPR = new ZigElementType("BINARY_OR_EXPR");
  IElementType BINARY_XOR_EXPR = new ZigElementType("BINARY_XOR_EXPR");
  IElementType BIT_SHIFT_EXPR = new ZigElementType("BIT_SHIFT_EXPR");
  IElementType BIT_SHIFT_OPERATOR = new ZigElementType("BIT_SHIFT_OPERATOR");
  IElementType BLOCK = new ZigElementType("BLOCK");
  IElementType BLOCK_BLOCK = new ZigElementType("BLOCK_BLOCK");
  IElementType BLOCK_EXPR = new ZigElementType("BLOCK_EXPR");
  IElementType BOOLEAN = new ZigElementType("BOOLEAN");
  IElementType BOOL_AND_EXPR = new ZigElementType("BOOL_AND_EXPR");
  IElementType BOOL_OR_EXPR = new ZigElementType("BOOL_OR_EXPR");
  IElementType BREAK_EXPR = new ZigElementType("BREAK_EXPR");
  IElementType CANCEL_EXPR = new ZigElementType("CANCEL_EXPR");
  IElementType CHAR = new ZigElementType("CHAR");
  IElementType COMPARISON_EXPR = new ZigElementType("COMPARISON_EXPR");
  IElementType COMPARISON_OPERATOR = new ZigElementType("COMPARISON_OPERATOR");
  IElementType COMP_TIME_BLOCK = new ZigElementType("COMP_TIME_BLOCK");
  IElementType COMP_TIME_EXPR_OR_BLOCK = new ZigElementType("COMP_TIME_EXPR_OR_BLOCK");
  IElementType CONTAINER_EXPR = new ZigElementType("CONTAINER_EXPR");
  IElementType CONTAINER_FIELD = new ZigElementType("CONTAINER_FIELD");
  IElementType CONTAINER_INIT_BODY = new ZigElementType("CONTAINER_INIT_BODY");
  IElementType CONTAINER_INIT_SUFFIX = new ZigElementType("CONTAINER_INIT_SUFFIX");
  IElementType CONTAINER_MEMBER = new ZigElementType("CONTAINER_MEMBER");
  IElementType CONTINUE_EXPR = new ZigElementType("CONTINUE_EXPR");
  IElementType CURLY_SUFFIX_EXPR = new ZigElementType("CURLY_SUFFIX_EXPR");
  IElementType DEFER = new ZigElementType("DEFER");
  IElementType DEFER_BLOCK = new ZigElementType("DEFER_BLOCK");
  IElementType ERROR_SET_EXPR = new ZigElementType("ERROR_SET_EXPR");
  IElementType EXPR = new ZigElementType("EXPR");
  IElementType EXTERN_DECLARATION = new ZigElementType("EXTERN_DECLARATION");
  IElementType FIELD_ACCESS_SUFFIX = new ZigElementType("FIELD_ACCESS_SUFFIX");
  IElementType FLOAT = new ZigElementType("FLOAT");
  IElementType FN_CALL_SUFFIX = new ZigElementType("FN_CALL_SUFFIX");
  IElementType FN_DECLARATION = new ZigElementType("FN_DECLARATION");
  IElementType FN_PROTO = new ZigElementType("FN_PROTO");
  IElementType FOR_BLOCK = new ZigElementType("FOR_BLOCK");
  IElementType FOR_OR_BLOCK = new ZigElementType("FOR_OR_BLOCK");
  IElementType GLOBAL_VAR_DECLARATION = new ZigElementType("GLOBAL_VAR_DECLARATION");
  IElementType GROUPED_EXPR = new ZigElementType("GROUPED_EXPR");
  IElementType IF_BLOCK = new ZigElementType("IF_BLOCK");
  IElementType IF_ERROR_BLOCK = new ZigElementType("IF_ERROR_BLOCK");
  IElementType IF_ERROR_EXPR_OR_BLOCK = new ZigElementType("IF_ERROR_EXPR_OR_BLOCK");
  IElementType IF_EXPR_OR_BLOCK = new ZigElementType("IF_EXPR_OR_BLOCK");
  IElementType INTEGER = new ZigElementType("INTEGER");
  IElementType KEYWORD_LITERAL = new ZigElementType("KEYWORD_LITERAL");
  IElementType LOCAL_VARIABLE_DECLARATION = new ZigElementType("LOCAL_VARIABLE_DECLARATION");
  IElementType MACRO_EXPR = new ZigElementType("MACRO_EXPR");
  IElementType MULTIPLY_EXPR = new ZigElementType("MULTIPLY_EXPR");
  IElementType MULTIPLY_OPERATOR = new ZigElementType("MULTIPLY_OPERATOR");
  IElementType NULL = new ZigElementType("NULL");
  IElementType OPTIONAL_SUFFIX = new ZigElementType("OPTIONAL_SUFFIX");
  IElementType OR_ELSE_EXPR = new ZigElementType("OR_ELSE_EXPR");
  IElementType PARAMETER_LIST = new ZigElementType("PARAMETER_LIST");
  IElementType PARAM_DECLARATION = new ZigElementType("PARAM_DECLARATION");
  IElementType POINTER_SUFFIX = new ZigElementType("POINTER_SUFFIX");
  IElementType PREFIX_OP = new ZigElementType("PREFIX_OP");
  IElementType PREFIX_OP_EXPR = new ZigElementType("PREFIX_OP_EXPR");
  IElementType RESUME_EXPR = new ZigElementType("RESUME_EXPR");
  IElementType RETURN_EXPR = new ZigElementType("RETURN_EXPR");
  IElementType SLICE_SUFFIX = new ZigElementType("SLICE_SUFFIX");
  IElementType STATEMENT = new ZigElementType("STATEMENT");
  IElementType STRING = new ZigElementType("STRING");
  IElementType STRUCT_LITERAL_FIELD = new ZigElementType("STRUCT_LITERAL_FIELD");
  IElementType SUFFIX_OP_EXPR = new ZigElementType("SUFFIX_OP_EXPR");
  IElementType SUSPEND_BLOCK = new ZigElementType("SUSPEND_BLOCK");
  IElementType SUSPEND_EXPR_OR_BLOCK = new ZigElementType("SUSPEND_EXPR_OR_BLOCK");
  IElementType SWITCH_ITEM = new ZigElementType("SWITCH_ITEM");
  IElementType SWITCH_PRONG = new ZigElementType("SWITCH_PRONG");
  IElementType SWITCH_STATEMENT = new ZigElementType("SWITCH_STATEMENT");
  IElementType SYMBOL = new ZigElementType("SYMBOL");
  IElementType TEST_BLOCK = new ZigElementType("TEST_BLOCK");
  IElementType TEST_DECLARATION = new ZigElementType("TEST_DECLARATION");
  IElementType TEST_EXPR_OR_BLOCK = new ZigElementType("TEST_EXPR_OR_BLOCK");
  IElementType TRY_EXPR = new ZigElementType("TRY_EXPR");
  IElementType TYPE_EXPR = new ZigElementType("TYPE_EXPR");
  IElementType UNWRAP_ERROR = new ZigElementType("UNWRAP_ERROR");
  IElementType UNWRAP_EXPR = new ZigElementType("UNWRAP_EXPR");
  IElementType UNWRAP_NULLABLE = new ZigElementType("UNWRAP_NULLABLE");
  IElementType USE_DECLARATION = new ZigElementType("USE_DECLARATION");
  IElementType VARIABLE_DECLARATION = new ZigElementType("VARIABLE_DECLARATION");
  IElementType WHILE_BLOCK = new ZigElementType("WHILE_BLOCK");
  IElementType WHILE_EXPR_OR_BLOCK = new ZigElementType("WHILE_EXPR_OR_BLOCK");

  IElementType ALIGN_KEYWORD = new ZigTokenType("ALIGN_KEYWORD");
  IElementType AND_ASSIGN_SYM = new ZigTokenType("AND_ASSIGN_SYM");
  IElementType AND_KEYWORD = new ZigTokenType("AND_KEYWORD");
  IElementType AND_SYM = new ZigTokenType("AND_SYM");
  IElementType ARROW_SYM = new ZigTokenType("ARROW_SYM");
  IElementType ASM_KEYWORD = new ZigTokenType("ASM_KEYWORD");
  IElementType ASYNC_KEYWORD = new ZigTokenType("ASYNC_KEYWORD");
  IElementType AT_SYM = new ZigTokenType("AT_SYM");
  IElementType AWAIT_KEYWORD = new ZigTokenType("AWAIT_KEYWORD");
  IElementType BITWISE_NOT_SYM = new ZigTokenType("BITWISE_NOT_SYM");
  IElementType BREAK_KEYWORD = new ZigTokenType("BREAK_KEYWORD");
  IElementType BUILTIN_FUNCTION = new ZigTokenType("BUILTIN_FUNCTION");
  IElementType CANCEL_KEYWORD = new ZigTokenType("CANCEL_KEYWORD");
  IElementType CATCH_KEYWORD = new ZigTokenType("CATCH_KEYWORD");
  IElementType CHAR_LITERAL = new ZigTokenType("CHAR_LITERAL");
  IElementType COLON_SYM = new ZigTokenType("COLON_SYM");
  IElementType COMMA_SYM = new ZigTokenType("COMMA_SYM");
  IElementType COMPTIME_KEYWORD = new ZigTokenType("COMPTIME_KEYWORD");
  IElementType CONST_KEYWORD = new ZigTokenType("CONST_KEYWORD");
  IElementType CONTINUE_KEYWORD = new ZigTokenType("CONTINUE_KEYWORD");
  IElementType DEFERROR_KEYWORD = new ZigTokenType("DEFERROR_KEYWORD");
  IElementType DEFER_KEYWORD = new ZigTokenType("DEFER_KEYWORD");
  IElementType DIV_ASSIGN_SYM = new ZigTokenType("DIV_ASSIGN_SYM");
  IElementType DIV_SYM = new ZigTokenType("DIV_SYM");
  IElementType DOT_SYM = new ZigTokenType("DOT_SYM");
  IElementType ELSE_KEYWORD = new ZigTokenType("ELSE_KEYWORD");
  IElementType ENUM_KEYWORD = new ZigTokenType("ENUM_KEYWORD");
  IElementType EQUAL_SYM = new ZigTokenType("EQUAL_SYM");
  IElementType EQ_SYM = new ZigTokenType("EQ_SYM");
  IElementType ERROR_KEYWORD = new ZigTokenType("ERROR_KEYWORD");
  IElementType EXPONENT_ASSIGN_SYM = new ZigTokenType("EXPONENT_ASSIGN_SYM");
  IElementType EXPONENT_SYM = new ZigTokenType("EXPONENT_SYM");
  IElementType EXPORT_KEYWORD = new ZigTokenType("EXPORT_KEYWORD");
  IElementType EXTERN_KEYWORD = new ZigTokenType("EXTERN_KEYWORD");
  IElementType FALSE_KEYWORD = new ZigTokenType("FALSE_KEYWORD");
  IElementType FLOAT_LITERAL = new ZigTokenType("FLOAT_LITERAL");
  IElementType FN_KEYWORD = new ZigTokenType("FN_KEYWORD");
  IElementType FOR_KEYWORD = new ZigTokenType("FOR_KEYWORD");
  IElementType GE_SYM = new ZigTokenType("GE_SYM");
  IElementType GT_SYM = new ZigTokenType("GT_SYM");
  IElementType IF_KEYWORD = new ZigTokenType("IF_KEYWORD");
  IElementType INC_SYM = new ZigTokenType("INC_SYM");
  IElementType INLINE_KEYWORD = new ZigTokenType("INLINE_KEYWORD");
  IElementType INT_LITERAL = new ZigTokenType("INT_LITERAL");
  IElementType LEFT_BRACE = new ZigTokenType("LEFT_BRACE");
  IElementType LEFT_BRACKET = new ZigTokenType("LEFT_BRACKET");
  IElementType LEFT_PAREN = new ZigTokenType("LEFT_PAREN");
  IElementType LE_SYM = new ZigTokenType("LE_SYM");
  IElementType LT_SYM = new ZigTokenType("LT_SYM");
  IElementType MINUS_ASSIGN_SYM = new ZigTokenType("MINUS_ASSIGN_SYM");
  IElementType MINUS_MOD_ASSIGN_SYM = new ZigTokenType("MINUS_MOD_ASSIGN_SYM");
  IElementType MINUS_MOD_SYM = new ZigTokenType("MINUS_MOD_SYM");
  IElementType MINUS_SYM = new ZigTokenType("MINUS_SYM");
  IElementType MOD_ASSIGN_SYM = new ZigTokenType("MOD_ASSIGN_SYM");
  IElementType MOD_SYM = new ZigTokenType("MOD_SYM");
  IElementType NAKEDCC_KEYWORD = new ZigTokenType("NAKEDCC_KEYWORD");
  IElementType NOALIAS_KEYWORD = new ZigTokenType("NOALIAS_KEYWORD");
  IElementType NOT_SYM = new ZigTokenType("NOT_SYM");
  IElementType NULL_KEYWORD = new ZigTokenType("NULL_KEYWORD");
  IElementType ORELSE_KEYWORD = new ZigTokenType("ORELSE_KEYWORD");
  IElementType OR_ASSIGN_SYM = new ZigTokenType("OR_ASSIGN_SYM");
  IElementType OR_KEYWORD = new ZigTokenType("OR_KEYWORD");
  IElementType PACKED_KEYWORD = new ZigTokenType("PACKED_KEYWORD");
  IElementType PLUS_ASSIGN_SYM = new ZigTokenType("PLUS_ASSIGN_SYM");
  IElementType PLUS_MOD_ASSIGN_SYM = new ZigTokenType("PLUS_MOD_ASSIGN_SYM");
  IElementType PLUS_MOD_SYM = new ZigTokenType("PLUS_MOD_SYM");
  IElementType PLUS_SYM = new ZigTokenType("PLUS_SYM");
  IElementType PUB_KEYWORD = new ZigTokenType("PUB_KEYWORD");
  IElementType QUESTION_SYM = new ZigTokenType("QUESTION_SYM");
  IElementType RANGE_SYM = new ZigTokenType("RANGE_SYM");
  IElementType RAW_STR = new ZigTokenType("RAW_STR");
  IElementType RESUME_KEYWORD = new ZigTokenType("RESUME_KEYWORD");
  IElementType RETURN_KEYWORD = new ZigTokenType("RETURN_KEYWORD");
  IElementType RIGHT_BRACE = new ZigTokenType("RIGHT_BRACE");
  IElementType RIGHT_BRACKET = new ZigTokenType("RIGHT_BRACKET");
  IElementType RIGHT_PAREN = new ZigTokenType("RIGHT_PAREN");
  IElementType SECTION_KEYWORD = new ZigTokenType("SECTION_KEYWORD");
  IElementType SEMICOLON_SYM = new ZigTokenType("SEMICOLON_SYM");
  IElementType SEP_SEP_SYM = new ZigTokenType("SEP_SEP_SYM");
  IElementType SEP_SYM = new ZigTokenType("SEP_SYM");
  IElementType SHL_ASSIGN_SYM = new ZigTokenType("SHL_ASSIGN_SYM");
  IElementType SHL_SYM = new ZigTokenType("SHL_SYM");
  IElementType SHR_ASSIGN_SYM = new ZigTokenType("SHR_ASSIGN_SYM");
  IElementType SHR_SYM = new ZigTokenType("SHR_SYM");
  IElementType SLICE_SYM = new ZigTokenType("SLICE_SYM");
  IElementType SMALL_ARROW_SYM = new ZigTokenType("SMALL_ARROW_SYM");
  IElementType STAR_ASSIGN_SYM = new ZigTokenType("STAR_ASSIGN_SYM");
  IElementType STAR_MOD_ASSIGN_SYM = new ZigTokenType("STAR_MOD_ASSIGN_SYM");
  IElementType STAR_MOD_SYM = new ZigTokenType("STAR_MOD_SYM");
  IElementType STAR_STAR_SYM = new ZigTokenType("STAR_STAR_SYM");
  IElementType STAR_SYM = new ZigTokenType("STAR_SYM");
  IElementType STDCALLCC_KEYWORD = new ZigTokenType("STDCALLCC_KEYWORD");
  IElementType STR = new ZigTokenType("STR");
  IElementType STRUCT_KEYWORD = new ZigTokenType("STRUCT_KEYWORD");
  IElementType SUSPEND_KEYWORD = new ZigTokenType("SUSPEND_KEYWORD");
  IElementType SWITCH_KEYWORD = new ZigTokenType("SWITCH_KEYWORD");
  IElementType SYM = new ZigTokenType("SYM");
  IElementType TEST_KEYWORD = new ZigTokenType("TEST_KEYWORD");
  IElementType THIS_KEYWORD = new ZigTokenType("THIS_KEYWORD");
  IElementType TIMES_ASSIGN_SYM = new ZigTokenType("TIMES_ASSIGN_SYM");
  IElementType TRUE_KEYWORD = new ZigTokenType("TRUE_KEYWORD");
  IElementType TRY_KEYWORD = new ZigTokenType("TRY_KEYWORD");
  IElementType UNDEFINED_KEYWORD = new ZigTokenType("UNDEFINED_KEYWORD");
  IElementType UNEQUAL_SYM = new ZigTokenType("UNEQUAL_SYM");
  IElementType UNION_KEYWORD = new ZigTokenType("UNION_KEYWORD");
  IElementType UNREACHABLE_KEYWORD = new ZigTokenType("UNREACHABLE_KEYWORD");
  IElementType USE_KEYWORD = new ZigTokenType("USE_KEYWORD");
  IElementType VAR_KEYWORD = new ZigTokenType("VAR_KEYWORD");
  IElementType VERY_QUESTION_SYM = new ZigTokenType("VERY_QUESTION_SYM");
  IElementType VOLATILE_KEYWORD = new ZigTokenType("VOLATILE_KEYWORD");
  IElementType WHILE_KEYWORD = new ZigTokenType("WHILE_KEYWORD");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == ADDITION_EXPR) {
        return new ZigAdditionExprImpl(node);
      }
      else if (type == ADDITION_OPERATOR) {
        return new ZigAdditionOperatorImpl(node);
      }
      else if (type == ARRAY_ACCESS_SUFFIX) {
        return new ZigArrayAccessSuffixImpl(node);
      }
      else if (type == ARRAY_EXPR) {
        return new ZigArrayExprImpl(node);
      }
      else if (type == ASM_CLOBBERS) {
        return new ZigAsmClobbersImpl(node);
      }
      else if (type == ASM_EXPR) {
        return new ZigAsmExprImpl(node);
      }
      else if (type == ASM_INPUT) {
        return new ZigAsmInputImpl(node);
      }
      else if (type == ASM_INPUT_ITEM) {
        return new ZigAsmInputItemImpl(node);
      }
      else if (type == ASM_OUTPUT) {
        return new ZigAsmOutputImpl(node);
      }
      else if (type == ASM_OUTPUT_ITEM) {
        return new ZigAsmOutputItemImpl(node);
      }
      else if (type == ASSIGN_EXPR) {
        return new ZigAssignExprImpl(node);
      }
      else if (type == ASSIGN_OPERATOR) {
        return new ZigAssignOperatorImpl(node);
      }
      else if (type == ASYNC_EXPR) {
        return new ZigAsyncExprImpl(node);
      }
      else if (type == AWAIT_EXPR) {
        return new ZigAwaitExprImpl(node);
      }
      else if (type == BINARY_AND_EXPR) {
        return new ZigBinaryAndExprImpl(node);
      }
      else if (type == BINARY_OR_EXPR) {
        return new ZigBinaryOrExprImpl(node);
      }
      else if (type == BINARY_XOR_EXPR) {
        return new ZigBinaryXorExprImpl(node);
      }
      else if (type == BIT_SHIFT_EXPR) {
        return new ZigBitShiftExprImpl(node);
      }
      else if (type == BIT_SHIFT_OPERATOR) {
        return new ZigBitShiftOperatorImpl(node);
      }
      else if (type == BLOCK) {
        return new ZigBlockImpl(node);
      }
      else if (type == BLOCK_BLOCK) {
        return new ZigBlockBlockImpl(node);
      }
      else if (type == BLOCK_EXPR) {
        return new ZigBlockExprImpl(node);
      }
      else if (type == BOOLEAN) {
        return new ZigBooleanImpl(node);
      }
      else if (type == BOOL_AND_EXPR) {
        return new ZigBoolAndExprImpl(node);
      }
      else if (type == BOOL_OR_EXPR) {
        return new ZigBoolOrExprImpl(node);
      }
      else if (type == BREAK_EXPR) {
        return new ZigBreakExprImpl(node);
      }
      else if (type == CANCEL_EXPR) {
        return new ZigCancelExprImpl(node);
      }
      else if (type == CHAR) {
        return new ZigCharImpl(node);
      }
      else if (type == COMPARISON_EXPR) {
        return new ZigComparisonExprImpl(node);
      }
      else if (type == COMPARISON_OPERATOR) {
        return new ZigComparisonOperatorImpl(node);
      }
      else if (type == COMP_TIME_BLOCK) {
        return new ZigCompTimeBlockImpl(node);
      }
      else if (type == COMP_TIME_EXPR_OR_BLOCK) {
        return new ZigCompTimeExprOrBlockImpl(node);
      }
      else if (type == CONTAINER_EXPR) {
        return new ZigContainerExprImpl(node);
      }
      else if (type == CONTAINER_FIELD) {
        return new ZigContainerFieldImpl(node);
      }
      else if (type == CONTAINER_INIT_BODY) {
        return new ZigContainerInitBodyImpl(node);
      }
      else if (type == CONTAINER_INIT_SUFFIX) {
        return new ZigContainerInitSuffixImpl(node);
      }
      else if (type == CONTAINER_MEMBER) {
        return new ZigContainerMemberImpl(node);
      }
      else if (type == CONTINUE_EXPR) {
        return new ZigContinueExprImpl(node);
      }
      else if (type == CURLY_SUFFIX_EXPR) {
        return new ZigCurlySuffixExprImpl(node);
      }
      else if (type == DEFER) {
        return new ZigDeferImpl(node);
      }
      else if (type == DEFER_BLOCK) {
        return new ZigDeferBlockImpl(node);
      }
      else if (type == ERROR_SET_EXPR) {
        return new ZigErrorSetExprImpl(node);
      }
      else if (type == EXTERN_DECLARATION) {
        return new ZigExternDeclarationImpl(node);
      }
      else if (type == FIELD_ACCESS_SUFFIX) {
        return new ZigFieldAccessSuffixImpl(node);
      }
      else if (type == FLOAT) {
        return new ZigFloatImpl(node);
      }
      else if (type == FN_CALL_SUFFIX) {
        return new ZigFnCallSuffixImpl(node);
      }
      else if (type == FN_DECLARATION) {
        return new ZigFnDeclarationImpl(node);
      }
      else if (type == FN_PROTO) {
        return new ZigFnProtoImpl(node);
      }
      else if (type == FOR_BLOCK) {
        return new ZigForBlockImpl(node);
      }
      else if (type == FOR_OR_BLOCK) {
        return new ZigForOrBlockImpl(node);
      }
      else if (type == GLOBAL_VAR_DECLARATION) {
        return new ZigGlobalVarDeclarationImpl(node);
      }
      else if (type == GROUPED_EXPR) {
        return new ZigGroupedExprImpl(node);
      }
      else if (type == IF_BLOCK) {
        return new ZigIfBlockImpl(node);
      }
      else if (type == IF_ERROR_BLOCK) {
        return new ZigIfErrorBlockImpl(node);
      }
      else if (type == IF_ERROR_EXPR_OR_BLOCK) {
        return new ZigIfErrorExprOrBlockImpl(node);
      }
      else if (type == IF_EXPR_OR_BLOCK) {
        return new ZigIfExprOrBlockImpl(node);
      }
      else if (type == INTEGER) {
        return new ZigIntegerImpl(node);
      }
      else if (type == KEYWORD_LITERAL) {
        return new ZigKeywordLiteralImpl(node);
      }
      else if (type == LOCAL_VARIABLE_DECLARATION) {
        return new ZigLocalVariableDeclarationImpl(node);
      }
      else if (type == MACRO_EXPR) {
        return new ZigMacroExprImpl(node);
      }
      else if (type == MULTIPLY_EXPR) {
        return new ZigMultiplyExprImpl(node);
      }
      else if (type == MULTIPLY_OPERATOR) {
        return new ZigMultiplyOperatorImpl(node);
      }
      else if (type == NULL) {
        return new ZigNullImpl(node);
      }
      else if (type == OPTIONAL_SUFFIX) {
        return new ZigOptionalSuffixImpl(node);
      }
      else if (type == OR_ELSE_EXPR) {
        return new ZigOrElseExprImpl(node);
      }
      else if (type == PARAMETER_LIST) {
        return new ZigParameterListImpl(node);
      }
      else if (type == PARAM_DECLARATION) {
        return new ZigParamDeclarationImpl(node);
      }
      else if (type == POINTER_SUFFIX) {
        return new ZigPointerSuffixImpl(node);
      }
      else if (type == PREFIX_OP) {
        return new ZigPrefixOpImpl(node);
      }
      else if (type == PREFIX_OP_EXPR) {
        return new ZigPrefixOpExprImpl(node);
      }
      else if (type == RESUME_EXPR) {
        return new ZigResumeExprImpl(node);
      }
      else if (type == RETURN_EXPR) {
        return new ZigReturnExprImpl(node);
      }
      else if (type == SLICE_SUFFIX) {
        return new ZigSliceSuffixImpl(node);
      }
      else if (type == STATEMENT) {
        return new ZigStatementImpl(node);
      }
      else if (type == STRING) {
        return new ZigStringImpl(node);
      }
      else if (type == STRUCT_LITERAL_FIELD) {
        return new ZigStructLiteralFieldImpl(node);
      }
      else if (type == SUFFIX_OP_EXPR) {
        return new ZigSuffixOpExprImpl(node);
      }
      else if (type == SUSPEND_BLOCK) {
        return new ZigSuspendBlockImpl(node);
      }
      else if (type == SUSPEND_EXPR_OR_BLOCK) {
        return new ZigSuspendExprOrBlockImpl(node);
      }
      else if (type == SWITCH_ITEM) {
        return new ZigSwitchItemImpl(node);
      }
      else if (type == SWITCH_PRONG) {
        return new ZigSwitchProngImpl(node);
      }
      else if (type == SWITCH_STATEMENT) {
        return new ZigSwitchStatementImpl(node);
      }
      else if (type == SYMBOL) {
        return new ZigSymbolImpl(node);
      }
      else if (type == TEST_BLOCK) {
        return new ZigTestBlockImpl(node);
      }
      else if (type == TEST_DECLARATION) {
        return new ZigTestDeclarationImpl(node);
      }
      else if (type == TEST_EXPR_OR_BLOCK) {
        return new ZigTestExprOrBlockImpl(node);
      }
      else if (type == TRY_EXPR) {
        return new ZigTryExprImpl(node);
      }
      else if (type == TYPE_EXPR) {
        return new ZigTypeExprImpl(node);
      }
      else if (type == UNWRAP_ERROR) {
        return new ZigUnwrapErrorImpl(node);
      }
      else if (type == UNWRAP_EXPR) {
        return new ZigUnwrapExprImpl(node);
      }
      else if (type == UNWRAP_NULLABLE) {
        return new ZigUnwrapNullableImpl(node);
      }
      else if (type == USE_DECLARATION) {
        return new ZigUseDeclarationImpl(node);
      }
      else if (type == VARIABLE_DECLARATION) {
        return new ZigVariableDeclarationImpl(node);
      }
      else if (type == WHILE_BLOCK) {
        return new ZigWhileBlockImpl(node);
      }
      else if (type == WHILE_EXPR_OR_BLOCK) {
        return new ZigWhileExprOrBlockImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}

// This is a generated file. Not intended for manual editing.
package org.ziglang;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static org.ziglang.psi.ZigTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class ZigParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType root_, PsiBuilder builder_) {
    parseLight(root_, builder_);
    return builder_.getTreeBuilt();
  }

  public void parseLight(IElementType root_, PsiBuilder builder_) {
    boolean result_;
    builder_ = adapt_builder_(root_, builder_, this, EXTENDS_SETS_);
    Marker marker_ = enter_section_(builder_, 0, _COLLAPSE_, null);
    result_ = parse_root_(root_, builder_);
    exit_section_(builder_, 0, marker_, root_, result_, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType root_, PsiBuilder builder_) {
    return parse_root_(root_, builder_, 0);
  }

  static boolean parse_root_(IElementType root_, PsiBuilder builder_, int level_) {
    return zigFile(builder_, level_ + 1);
  }

  public static final TokenSet[] EXTENDS_SETS_ = new TokenSet[] {
    create_token_set_(ADDITION_EXPR, ARRAY_EXPR, ASM_EXPR, ASSIGN_EXPR,
      ASYNC_EXPR, AWAIT_EXPR, BINARY_AND_EXPR, BINARY_OR_EXPR,
      BINARY_XOR_EXPR, BIT_SHIFT_EXPR, BLOCK_EXPR, BOOLEAN,
      BOOL_AND_EXPR, BOOL_OR_EXPR, BREAK_EXPR, CANCEL_EXPR,
      CHAR, COMPARISON_EXPR, CONTAINER_EXPR, CONTINUE_EXPR,
      CURLY_SUFFIX_EXPR, ERROR_SET_EXPR, EXPR, FLOAT,
      FN_PROTO, GROUPED_EXPR, INTEGER, KEYWORD_LITERAL,
      MACRO_EXPR, MULTIPLY_EXPR, NULL, OR_ELSE_EXPR,
      PREFIX_OP_EXPR, RESUME_EXPR, RETURN_EXPR, STRING,
      SUFFIX_OP_EXPR, SYMBOL, TRY_EXPR, TYPE_EXPR,
      UNWRAP_EXPR),
  };

  /* ********************************************************** */
  // PLUS_SYM | MINUS_SYM | INC_SYM | PLUS_MOD_SYM | MINUS_MOD_SYM
  public static boolean additionOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "additionOperator")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ADDITION_OPERATOR, "<addition operator>");
    result_ = consumeToken(builder_, PLUS_SYM);
    if (!result_) result_ = consumeToken(builder_, MINUS_SYM);
    if (!result_) result_ = consumeToken(builder_, INC_SYM);
    if (!result_) result_ = consumeToken(builder_, PLUS_MOD_SYM);
    if (!result_) result_ = consumeToken(builder_, MINUS_MOD_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_BRACKET expr RIGHT_BRACKET
  public static boolean arrayAccessSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayAccessSuffix")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    exit_section_(builder_, marker_, ARRAY_ACCESS_SUFFIX, result_);
    return result_;
  }

  /* ********************************************************** */
  // COLON_SYM (string (COMMA_SYM string)*)?
  public static boolean asmClobbers(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmClobbers")) return false;
    if (!nextTokenIs(builder_, COLON_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_SYM);
    result_ = result_ && asmClobbers_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASM_CLOBBERS, result_);
    return result_;
  }

  // (string (COMMA_SYM string)*)?
  private static boolean asmClobbers_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmClobbers_1")) return false;
    asmClobbers_1_0(builder_, level_ + 1);
    return true;
  }

  // string (COMMA_SYM string)*
  private static boolean asmClobbers_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmClobbers_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = string(builder_, level_ + 1);
    result_ = result_ && asmClobbers_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM string)*
  private static boolean asmClobbers_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmClobbers_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!asmClobbers_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "asmClobbers_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM string
  private static boolean asmClobbers_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmClobbers_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && string(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // COLON_SYM (asmInputItem (COMMA_SYM asmInputItem)*)? asmClobbers?
  public static boolean asmInput(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInput")) return false;
    if (!nextTokenIs(builder_, COLON_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_SYM);
    result_ = result_ && asmInput_1(builder_, level_ + 1);
    result_ = result_ && asmInput_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASM_INPUT, result_);
    return result_;
  }

  // (asmInputItem (COMMA_SYM asmInputItem)*)?
  private static boolean asmInput_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInput_1")) return false;
    asmInput_1_0(builder_, level_ + 1);
    return true;
  }

  // asmInputItem (COMMA_SYM asmInputItem)*
  private static boolean asmInput_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInput_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = asmInputItem(builder_, level_ + 1);
    result_ = result_ && asmInput_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM asmInputItem)*
  private static boolean asmInput_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInput_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!asmInput_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "asmInput_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM asmInputItem
  private static boolean asmInput_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInput_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && asmInputItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // asmClobbers?
  private static boolean asmInput_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInput_2")) return false;
    asmClobbers(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LEFT_BRACKET symbol RIGHT_BRACKET string LEFT_PAREN expr RIGHT_PAREN
  public static boolean asmInputItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmInputItem")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    result_ = result_ && string(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, ASM_INPUT_ITEM, result_);
    return result_;
  }

  /* ********************************************************** */
  // COLON_SYM (asmOutputItem (COMMA_SYM asmOutputItem)*)? asmInput?
  public static boolean asmOutput(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutput")) return false;
    if (!nextTokenIs(builder_, COLON_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_SYM);
    result_ = result_ && asmOutput_1(builder_, level_ + 1);
    result_ = result_ && asmOutput_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASM_OUTPUT, result_);
    return result_;
  }

  // (asmOutputItem (COMMA_SYM asmOutputItem)*)?
  private static boolean asmOutput_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutput_1")) return false;
    asmOutput_1_0(builder_, level_ + 1);
    return true;
  }

  // asmOutputItem (COMMA_SYM asmOutputItem)*
  private static boolean asmOutput_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutput_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = asmOutputItem(builder_, level_ + 1);
    result_ = result_ && asmOutput_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM asmOutputItem)*
  private static boolean asmOutput_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutput_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!asmOutput_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "asmOutput_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM asmOutputItem
  private static boolean asmOutput_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutput_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && asmOutputItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // asmInput?
  private static boolean asmOutput_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutput_2")) return false;
    asmInput(builder_, level_ + 1);
    return true;
  }

  /* ********************************************************** */
  // LEFT_BRACKET symbol RIGHT_BRACKET string LEFT_PAREN (symbol | SMALL_ARROW_SYM typeExpr) RIGHT_PAREN
  public static boolean asmOutputItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutputItem")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    result_ = result_ && string(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && asmOutputItem_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, ASM_OUTPUT_ITEM, result_);
    return result_;
  }

  // symbol | SMALL_ARROW_SYM typeExpr
  private static boolean asmOutputItem_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutputItem_5")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    if (!result_) result_ = asmOutputItem_5_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // SMALL_ARROW_SYM typeExpr
  private static boolean asmOutputItem_5_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmOutputItem_5_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SMALL_ARROW_SYM);
    result_ = result_ && expr(builder_, level_ + 1, 18);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // EQ_SYM
  //  | TIMES_ASSIGN_SYM
  //  | DIV_ASSIGN_SYM
  //  | MOD_ASSIGN_SYM
  //  | PLUS_ASSIGN_SYM
  //  | STAR_ASSIGN_SYM
  //  | MINUS_ASSIGN_SYM
  //  | SHL_ASSIGN_SYM
  //  | SHR_ASSIGN_SYM
  //  | AND_ASSIGN_SYM
  //  | EXPONENT_ASSIGN_SYM
  //  | OR_ASSIGN_SYM
  //  | STAR_MOD_ASSIGN_SYM
  //  | PLUS_MOD_ASSIGN_SYM
  //  | MINUS_MOD_ASSIGN_SYM
  public static boolean assignOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "assignOperator")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, ASSIGN_OPERATOR, "<assign operator>");
    result_ = consumeToken(builder_, EQ_SYM);
    if (!result_) result_ = consumeToken(builder_, TIMES_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, DIV_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, MOD_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, PLUS_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, STAR_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, MINUS_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, SHL_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, SHR_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, AND_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, EXPONENT_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, OR_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, STAR_MOD_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, PLUS_MOD_ASSIGN_SYM);
    if (!result_) result_ = consumeToken(builder_, MINUS_MOD_ASSIGN_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // SHL_SYM | SHR_SYM
  public static boolean bitShiftOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "bitShiftOperator")) return false;
    if (!nextTokenIs(builder_, "<bit shift operator>", SHL_SYM, SHR_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BIT_SHIFT_OPERATOR, "<bit shift operator>");
    result_ = consumeToken(builder_, SHL_SYM);
    if (!result_) result_ = consumeToken(builder_, SHR_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (symbol COLON_SYM)? LEFT_BRACE statement* RIGHT_BRACE
  public static boolean block(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block")) return false;
    if (!nextTokenIs(builder_, "<block>", LEFT_BRACE, SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BLOCK, "<block>");
    result_ = block_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && block_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (symbol COLON_SYM)?
  private static boolean block_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_0")) return false;
    block_0_0(builder_, level_ + 1);
    return true;
  }

  // symbol COLON_SYM
  private static boolean block_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // statement*
  private static boolean block_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "block_2")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!statement(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "block_2", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // block
  //  | ifBlock
  //  | ifErrorBlock
  //  | testBlock
  //  | whileBlock
  //  | forBlock
  //  | switchStatement
  //  | compTimeBlock
  //  | suspendBlock
  public static boolean blockBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BLOCK_BLOCK, "<block block>");
    result_ = block(builder_, level_ + 1);
    if (!result_) result_ = ifBlock(builder_, level_ + 1);
    if (!result_) result_ = ifErrorBlock(builder_, level_ + 1);
    if (!result_) result_ = testBlock(builder_, level_ + 1);
    if (!result_) result_ = whileBlock(builder_, level_ + 1);
    if (!result_) result_ = forBlock(builder_, level_ + 1);
    if (!result_) result_ = switchStatement(builder_, level_ + 1);
    if (!result_) result_ = compTimeBlock(builder_, level_ + 1);
    if (!result_) result_ = suspendBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // COMPTIME_KEYWORD block
  public static boolean compTimeBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compTimeBlock")) return false;
    if (!nextTokenIs(builder_, COMPTIME_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMPTIME_KEYWORD);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, COMP_TIME_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // COMPTIME_KEYWORD exprOrBlock
  public static boolean compTimeExprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "compTimeExprOrBlock")) return false;
    if (!nextTokenIs(builder_, COMPTIME_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMPTIME_KEYWORD);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, COMP_TIME_EXPR_OR_BLOCK, result_);
    return result_;
  }

  /* ********************************************************** */
  // EQUAL_SYM
  //  | UNEQUAL_SYM
  //  | LT_SYM
  //  | GT_SYM
  //  | LE_SYM
  //  | GE_SYM
  public static boolean comparisonOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "comparisonOperator")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, COMPARISON_OPERATOR, "<comparison operator>");
    result_ = consumeToken(builder_, EQUAL_SYM);
    if (!result_) result_ = consumeToken(builder_, UNEQUAL_SYM);
    if (!result_) result_ = consumeToken(builder_, LT_SYM);
    if (!result_) result_ = consumeToken(builder_, GT_SYM);
    if (!result_) result_ = consumeToken(builder_, LE_SYM);
    if (!result_) result_ = consumeToken(builder_, GE_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // symbol (COLON_SYM primary)? (EQ_SYM expr)? COMMA_SYM
  public static boolean containerField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerField")) return false;
    if (!nextTokenIs(builder_, SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && containerField_1(builder_, level_ + 1);
    result_ = result_ && containerField_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COMMA_SYM);
    exit_section_(builder_, marker_, CONTAINER_FIELD, result_);
    return result_;
  }

  // (COLON_SYM primary)?
  private static boolean containerField_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerField_1")) return false;
    containerField_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM primary
  private static boolean containerField_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerField_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_SYM);
    result_ = result_ && expr(builder_, level_ + 1, 22);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (EQ_SYM expr)?
  private static boolean containerField_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerField_2")) return false;
    containerField_2_0(builder_, level_ + 1);
    return true;
  }

  // EQ_SYM expr
  private static boolean containerField_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerField_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQ_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // structLiteralField (COMMA_SYM structLiteralField)*
  //  | expr (COMMA_SYM expr)*
  public static boolean containerInitBody(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONTAINER_INIT_BODY, "<container init body>");
    result_ = containerInitBody_0(builder_, level_ + 1);
    if (!result_) result_ = containerInitBody_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // structLiteralField (COMMA_SYM structLiteralField)*
  private static boolean containerInitBody_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = structLiteralField(builder_, level_ + 1);
    result_ = result_ && containerInitBody_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM structLiteralField)*
  private static boolean containerInitBody_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!containerInitBody_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "containerInitBody_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM structLiteralField
  private static boolean containerInitBody_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && structLiteralField(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // expr (COMMA_SYM expr)*
  private static boolean containerInitBody_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expr(builder_, level_ + 1, -1);
    result_ = result_ && containerInitBody_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM expr)*
  private static boolean containerInitBody_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody_1_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!containerInitBody_1_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "containerInitBody_1_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM expr
  private static boolean containerInitBody_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitBody_1_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_BRACE (containerInitBody COMMA_SYM?)? RIGHT_BRACE
  public static boolean containerInitSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitSuffix")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACE)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && containerInitSuffix_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, CONTAINER_INIT_SUFFIX, result_);
    return result_;
  }

  // (containerInitBody COMMA_SYM?)?
  private static boolean containerInitSuffix_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitSuffix_1")) return false;
    containerInitSuffix_1_0(builder_, level_ + 1);
    return true;
  }

  // containerInitBody COMMA_SYM?
  private static boolean containerInitSuffix_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitSuffix_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = containerInitBody(builder_, level_ + 1);
    result_ = result_ && containerInitSuffix_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMMA_SYM?
  private static boolean containerInitSuffix_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerInitSuffix_1_0_1")) return false;
    consumeToken(builder_, COMMA_SYM);
    return true;
  }

  /* ********************************************************** */
  // containerField
  //  | fnDeclaration
  //  | globalVarDeclaration
  public static boolean containerMember(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerMember")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONTAINER_MEMBER, "<container member>");
    result_ = containerField(builder_, level_ + 1);
    if (!result_) result_ = fnDeclaration(builder_, level_ + 1);
    if (!result_) result_ = globalVarDeclaration(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // (DEFER_KEYWORD | DEFERROR_KEYWORD) expr
  public static boolean defer(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "defer")) return false;
    if (!nextTokenIs(builder_, "<defer>", DEFERROR_KEYWORD, DEFER_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, DEFER, "<defer>");
    result_ = defer_0(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // DEFER_KEYWORD | DEFERROR_KEYWORD
  private static boolean defer_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "defer_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, DEFER_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, DEFERROR_KEYWORD);
    return result_;
  }

  /* ********************************************************** */
  // (DEFER_KEYWORD | DEFERROR_KEYWORD) block
  public static boolean deferBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "deferBlock")) return false;
    if (!nextTokenIs(builder_, "<defer block>", DEFERROR_KEYWORD, DEFER_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, DEFER_BLOCK, "<defer block>");
    result_ = deferBlock_0(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // DEFER_KEYWORD | DEFERROR_KEYWORD
  private static boolean deferBlock_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "deferBlock_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, DEFER_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, DEFERROR_KEYWORD);
    return result_;
  }

  /* ********************************************************** */
  // block | expr
  static boolean exprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "exprOrBlock")) return false;
    boolean result_;
    result_ = block(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    return result_;
  }

  /* ********************************************************** */
  // PUB_KEYWORD? EXTERN_KEYWORD string?
  //   (fnProto | variableDeclaration)
  //  SEMICOLON_SYM
  public static boolean externDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDeclaration")) return false;
    if (!nextTokenIs(builder_, "<extern declaration>", EXTERN_KEYWORD, PUB_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, EXTERN_DECLARATION, "<extern declaration>");
    result_ = externDeclaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EXTERN_KEYWORD);
    result_ = result_ && externDeclaration_2(builder_, level_ + 1);
    result_ = result_ && externDeclaration_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PUB_KEYWORD?
  private static boolean externDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDeclaration_0")) return false;
    consumeToken(builder_, PUB_KEYWORD);
    return true;
  }

  // string?
  private static boolean externDeclaration_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDeclaration_2")) return false;
    string(builder_, level_ + 1);
    return true;
  }

  // fnProto | variableDeclaration
  private static boolean externDeclaration_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "externDeclaration_3")) return false;
    boolean result_;
    result_ = fnProto(builder_, level_ + 1);
    if (!result_) result_ = variableDeclaration(builder_, level_ + 1);
    return result_;
  }

  /* ********************************************************** */
  // DOT_SYM symbol
  public static boolean fieldAccessSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fieldAccessSuffix")) return false;
    if (!nextTokenIs(builder_, DOT_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    exit_section_(builder_, marker_, FIELD_ACCESS_SUFFIX, result_);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_PAREN (expr (COMMA_SYM expr)*)? RIGHT_PAREN
  public static boolean fnCallSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnCallSuffix")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && fnCallSuffix_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, FN_CALL_SUFFIX, result_);
    return result_;
  }

  // (expr (COMMA_SYM expr)*)?
  private static boolean fnCallSuffix_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnCallSuffix_1")) return false;
    fnCallSuffix_1_0(builder_, level_ + 1);
    return true;
  }

  // expr (COMMA_SYM expr)*
  private static boolean fnCallSuffix_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnCallSuffix_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = expr(builder_, level_ + 1, -1);
    result_ = result_ && fnCallSuffix_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM expr)*
  private static boolean fnCallSuffix_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnCallSuffix_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!fnCallSuffix_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "fnCallSuffix_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM expr
  private static boolean fnCallSuffix_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnCallSuffix_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PUB_KEYWORD? (INLINE_KEYWORD | EXPORT_KEYWORD)?
  //   fnProto block
  public static boolean fnDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FN_DECLARATION, "<fn declaration>");
    result_ = fnDeclaration_0(builder_, level_ + 1);
    result_ = result_ && fnDeclaration_1(builder_, level_ + 1);
    result_ = result_ && fnProto(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PUB_KEYWORD?
  private static boolean fnDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnDeclaration_0")) return false;
    consumeToken(builder_, PUB_KEYWORD);
    return true;
  }

  // (INLINE_KEYWORD | EXPORT_KEYWORD)?
  private static boolean fnDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnDeclaration_1")) return false;
    fnDeclaration_1_0(builder_, level_ + 1);
    return true;
  }

  // INLINE_KEYWORD | EXPORT_KEYWORD
  private static boolean fnDeclaration_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnDeclaration_1_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, INLINE_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, EXPORT_KEYWORD);
    return result_;
  }

  /* ********************************************************** */
  // (symbol COLON_SYM)? INLINE_KEYWORD?
  //  FOR_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //   (SEP_SYM STAR_SYM? symbol (COMMA_SYM symbol)? SEP_SYM)?
  //    block
  //   (ELSE_KEYWORD blockBlock)?
  public static boolean forBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_BLOCK, "<for block>");
    result_ = forBlock_0(builder_, level_ + 1);
    result_ = result_ && forBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, FOR_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && forBlock_6(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && forBlock_8(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (symbol COLON_SYM)?
  private static boolean forBlock_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_0")) return false;
    forBlock_0_0(builder_, level_ + 1);
    return true;
  }

  // symbol COLON_SYM
  private static boolean forBlock_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // INLINE_KEYWORD?
  private static boolean forBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_1")) return false;
    consumeToken(builder_, INLINE_KEYWORD);
    return true;
  }

  // (SEP_SYM STAR_SYM? symbol (COMMA_SYM symbol)? SEP_SYM)?
  private static boolean forBlock_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_6")) return false;
    forBlock_6_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol (COMMA_SYM symbol)? SEP_SYM
  private static boolean forBlock_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && forBlock_6_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && forBlock_6_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean forBlock_6_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_6_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  // (COMMA_SYM symbol)?
  private static boolean forBlock_6_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_6_0_3")) return false;
    forBlock_6_0_3_0(builder_, level_ + 1);
    return true;
  }

  // COMMA_SYM symbol
  private static boolean forBlock_6_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_6_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ELSE_KEYWORD blockBlock)?
  private static boolean forBlock_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_8")) return false;
    forBlock_8_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD blockBlock
  private static boolean forBlock_8_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forBlock_8_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && blockBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (symbol COLON_SYM)? INLINE_KEYWORD?
  //  FOR_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //   (SEP_SYM STAR_SYM? symbol (COMMA_SYM symbol)? SEP_SYM)?
  //    exprOrBlock
  //   (ELSE_KEYWORD blockExpr)?
  public static boolean forOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FOR_OR_BLOCK, "<for or block>");
    result_ = forOrBlock_0(builder_, level_ + 1);
    result_ = result_ && forOrBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, FOR_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && forOrBlock_6(builder_, level_ + 1);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    result_ = result_ && forOrBlock_8(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (symbol COLON_SYM)?
  private static boolean forOrBlock_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_0")) return false;
    forOrBlock_0_0(builder_, level_ + 1);
    return true;
  }

  // symbol COLON_SYM
  private static boolean forOrBlock_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // INLINE_KEYWORD?
  private static boolean forOrBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_1")) return false;
    consumeToken(builder_, INLINE_KEYWORD);
    return true;
  }

  // (SEP_SYM STAR_SYM? symbol (COMMA_SYM symbol)? SEP_SYM)?
  private static boolean forOrBlock_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_6")) return false;
    forOrBlock_6_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol (COMMA_SYM symbol)? SEP_SYM
  private static boolean forOrBlock_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && forOrBlock_6_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && forOrBlock_6_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean forOrBlock_6_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_6_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  // (COMMA_SYM symbol)?
  private static boolean forOrBlock_6_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_6_0_3")) return false;
    forOrBlock_6_0_3_0(builder_, level_ + 1);
    return true;
  }

  // COMMA_SYM symbol
  private static boolean forOrBlock_6_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_6_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ELSE_KEYWORD blockExpr)?
  private static boolean forOrBlock_8(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_8")) return false;
    forOrBlock_8_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD blockExpr
  private static boolean forOrBlock_8_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "forOrBlock_8_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && blockExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // PUB_KEYWORD? EXPORT_KEYWORD?
  //   variableDeclaration
  //  SEMICOLON_SYM
  public static boolean globalVarDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "globalVarDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, GLOBAL_VAR_DECLARATION, "<global var declaration>");
    result_ = globalVarDeclaration_0(builder_, level_ + 1);
    result_ = result_ && globalVarDeclaration_1(builder_, level_ + 1);
    result_ = result_ && variableDeclaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PUB_KEYWORD?
  private static boolean globalVarDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "globalVarDeclaration_0")) return false;
    consumeToken(builder_, PUB_KEYWORD);
    return true;
  }

  // EXPORT_KEYWORD?
  private static boolean globalVarDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "globalVarDeclaration_1")) return false;
    consumeToken(builder_, EXPORT_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // IF_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //   block
  //  (ELSE_KEYWORD blockBlock)?
  public static boolean ifBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifBlock")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && ifBlock_5(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_BLOCK, result_);
    return result_;
  }

  // (ELSE_KEYWORD blockBlock)?
  private static boolean ifBlock_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifBlock_5")) return false;
    ifBlock_5_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD blockBlock
  private static boolean ifBlock_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifBlock_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && blockBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // IF_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //  (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //   block
  //  ELSE_KEYWORD SEP_SYM symbol SEP_SYM blockBlock
  public static boolean ifErrorBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorBlock")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && ifErrorBlock_4(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, ELSE_KEYWORD, SEP_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    result_ = result_ && blockBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_ERROR_BLOCK, result_);
    return result_;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean ifErrorBlock_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorBlock_4")) return false;
    ifErrorBlock_4_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean ifErrorBlock_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorBlock_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && ifErrorBlock_4_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean ifErrorBlock_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorBlock_4_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  /* ********************************************************** */
  // IF_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //  (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //   exprOrBlock
  //  ELSE_KEYWORD SEP_SYM symbol SEP_SYM blockExpr
  public static boolean ifErrorExprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorExprOrBlock")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && ifErrorExprOrBlock_4(builder_, level_ + 1);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, ELSE_KEYWORD, SEP_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    result_ = result_ && blockExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_ERROR_EXPR_OR_BLOCK, result_);
    return result_;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean ifErrorExprOrBlock_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorExprOrBlock_4")) return false;
    ifErrorExprOrBlock_4_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean ifErrorExprOrBlock_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorExprOrBlock_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && ifErrorExprOrBlock_4_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean ifErrorExprOrBlock_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifErrorExprOrBlock_4_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  /* ********************************************************** */
  // IF_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //   exprOrBlock
  //  (ELSE_KEYWORD exprOrBlock)?
  public static boolean ifExprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifExprOrBlock")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    result_ = result_ && ifExprOrBlock_5(builder_, level_ + 1);
    exit_section_(builder_, marker_, IF_EXPR_OR_BLOCK, result_);
    return result_;
  }

  // (ELSE_KEYWORD exprOrBlock)?
  private static boolean ifExprOrBlock_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifExprOrBlock_5")) return false;
    ifExprOrBlock_5_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD exprOrBlock
  private static boolean ifExprOrBlock_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "ifExprOrBlock_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // COMPTIME_KEYWORD? variableDeclaration
  //  SEMICOLON_SYM
  public static boolean localVariableDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "localVariableDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, LOCAL_VARIABLE_DECLARATION, "<local variable declaration>");
    result_ = localVariableDeclaration_0(builder_, level_ + 1);
    result_ = result_ && variableDeclaration(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // COMPTIME_KEYWORD?
  private static boolean localVariableDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "localVariableDeclaration_0")) return false;
    consumeToken(builder_, COMPTIME_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // SEP_SEP_SYM | STAR_SYM | DIV_SYM | MOD_SYM | STAR_STAR_SYM | STAR_MOD_SYM
  public static boolean multiplyOperator(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "multiplyOperator")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MULTIPLY_OPERATOR, "<multiply operator>");
    result_ = consumeToken(builder_, SEP_SEP_SYM);
    if (!result_) result_ = consumeToken(builder_, STAR_SYM);
    if (!result_) result_ = consumeToken(builder_, DIV_SYM);
    if (!result_) result_ = consumeToken(builder_, MOD_SYM);
    if (!result_) result_ = consumeToken(builder_, STAR_STAR_SYM);
    if (!result_) result_ = consumeToken(builder_, STAR_MOD_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  /* ********************************************************** */
  // DOT_SYM QUESTION_SYM
  public static boolean optionalSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "optionalSuffix")) return false;
    if (!nextTokenIs(builder_, DOT_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, DOT_SYM, QUESTION_SYM);
    exit_section_(builder_, marker_, OPTIONAL_SUFFIX, result_);
    return result_;
  }

  /* ********************************************************** */
  // (NOALIAS_KEYWORD | COMPTIME_KEYWORD)?
  //  (symbol COLON_SYM)?
  //  (typeExpr | VAR_KEYWORD | RANGE_SYM)
  public static boolean paramDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramDeclaration")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PARAM_DECLARATION, "<param declaration>");
    result_ = paramDeclaration_0(builder_, level_ + 1);
    result_ = result_ && paramDeclaration_1(builder_, level_ + 1);
    result_ = result_ && paramDeclaration_2(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (NOALIAS_KEYWORD | COMPTIME_KEYWORD)?
  private static boolean paramDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramDeclaration_0")) return false;
    paramDeclaration_0_0(builder_, level_ + 1);
    return true;
  }

  // NOALIAS_KEYWORD | COMPTIME_KEYWORD
  private static boolean paramDeclaration_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramDeclaration_0_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, NOALIAS_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, COMPTIME_KEYWORD);
    return result_;
  }

  // (symbol COLON_SYM)?
  private static boolean paramDeclaration_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramDeclaration_1")) return false;
    paramDeclaration_1_0(builder_, level_ + 1);
    return true;
  }

  // symbol COLON_SYM
  private static boolean paramDeclaration_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramDeclaration_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // typeExpr | VAR_KEYWORD | RANGE_SYM
  private static boolean paramDeclaration_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "paramDeclaration_2")) return false;
    boolean result_;
    result_ = expr(builder_, level_ + 1, 18);
    if (!result_) result_ = consumeToken(builder_, VAR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, RANGE_SYM);
    return result_;
  }

  /* ********************************************************** */
  // LEFT_PAREN (paramDeclaration (COMMA_SYM paramDeclaration)*)? RIGHT_PAREN
  public static boolean parameterList(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList")) return false;
    if (!nextTokenIs(builder_, LEFT_PAREN)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_PAREN);
    result_ = result_ && parameterList_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, PARAMETER_LIST, result_);
    return result_;
  }

  // (paramDeclaration (COMMA_SYM paramDeclaration)*)?
  private static boolean parameterList_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_1")) return false;
    parameterList_1_0(builder_, level_ + 1);
    return true;
  }

  // paramDeclaration (COMMA_SYM paramDeclaration)*
  private static boolean parameterList_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = paramDeclaration(builder_, level_ + 1);
    result_ = result_ && parameterList_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM paramDeclaration)*
  private static boolean parameterList_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!parameterList_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "parameterList_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM paramDeclaration
  private static boolean parameterList_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "parameterList_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && paramDeclaration(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // DOT_SYM STAR_SYM
  public static boolean pointerSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "pointerSuffix")) return false;
    if (!nextTokenIs(builder_, DOT_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, DOT_SYM, STAR_SYM);
    exit_section_(builder_, marker_, POINTER_SUFFIX, result_);
    return result_;
  }

  /* ********************************************************** */
  // NOT_SYM
  //  | MINUS_SYM
  //  | BITWISE_NOT_SYM
  //  | STAR_SYM
  //  | AND_SYM
  //  ( ALIGN_KEYWORD LEFT_PAREN
  //    expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN
  //  )? CONST_KEYWORD? VOLATILE_KEYWORD?
  //  | QUESTION_SYM
  //  | VERY_QUESTION_SYM
  //  | MINUS_MOD_SYM
  //  | TRY_KEYWORD
  //  | AWAIT_KEYWORD
  public static boolean prefixOp(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, PREFIX_OP, "<prefix op>");
    result_ = consumeToken(builder_, NOT_SYM);
    if (!result_) result_ = consumeToken(builder_, MINUS_SYM);
    if (!result_) result_ = consumeToken(builder_, BITWISE_NOT_SYM);
    if (!result_) result_ = consumeToken(builder_, STAR_SYM);
    if (!result_) result_ = prefixOp_4(builder_, level_ + 1);
    if (!result_) result_ = consumeToken(builder_, QUESTION_SYM);
    if (!result_) result_ = consumeToken(builder_, VERY_QUESTION_SYM);
    if (!result_) result_ = consumeToken(builder_, MINUS_MOD_SYM);
    if (!result_) result_ = consumeToken(builder_, TRY_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, AWAIT_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // AND_SYM
  //  ( ALIGN_KEYWORD LEFT_PAREN
  //    expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN
  //  )? CONST_KEYWORD? VOLATILE_KEYWORD?
  private static boolean prefixOp_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, AND_SYM);
    result_ = result_ && prefixOp_4_1(builder_, level_ + 1);
    result_ = result_ && prefixOp_4_2(builder_, level_ + 1);
    result_ = result_ && prefixOp_4_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ( ALIGN_KEYWORD LEFT_PAREN
  //    expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN
  //  )?
  private static boolean prefixOp_4_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4_1")) return false;
    prefixOp_4_1_0(builder_, level_ + 1);
    return true;
  }

  // ALIGN_KEYWORD LEFT_PAREN
  //    expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN
  private static boolean prefixOp_4_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ALIGN_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && prefixOp_4_1_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON_SYM integer COLON_SYM integer)?
  private static boolean prefixOp_4_1_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4_1_0_3")) return false;
    prefixOp_4_1_0_3_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM integer COLON_SYM integer
  private static boolean prefixOp_4_1_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4_1_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_SYM);
    result_ = result_ && integer(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    result_ = result_ && integer(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CONST_KEYWORD?
  private static boolean prefixOp_4_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4_2")) return false;
    consumeToken(builder_, CONST_KEYWORD);
    return true;
  }

  // VOLATILE_KEYWORD?
  private static boolean prefixOp_4_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOp_4_3")) return false;
    consumeToken(builder_, VOLATILE_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // LEFT_BRACKET expr SLICE_SYM expr? RIGHT_BRACKET
  public static boolean sliceSuffix(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "sliceSuffix")) return false;
    if (!nextTokenIs(builder_, LEFT_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, LEFT_BRACKET);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, SLICE_SYM);
    result_ = result_ && sliceSuffix_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    exit_section_(builder_, marker_, SLICE_SUFFIX, result_);
    return result_;
  }

  // expr?
  private static boolean sliceSuffix_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "sliceSuffix_3")) return false;
    expr(builder_, level_ + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // localVariableDeclaration
  //  | deferBlock
  //  | defer SEMICOLON_SYM
  //  | blockBlock
  //  | expr? SEMICOLON_SYM
  public static boolean statement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STATEMENT, "<statement>");
    result_ = localVariableDeclaration(builder_, level_ + 1);
    if (!result_) result_ = deferBlock(builder_, level_ + 1);
    if (!result_) result_ = statement_2(builder_, level_ + 1);
    if (!result_) result_ = blockBlock(builder_, level_ + 1);
    if (!result_) result_ = statement_4(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // defer SEMICOLON_SYM
  private static boolean statement_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = defer(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // expr? SEMICOLON_SYM
  private static boolean statement_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_4")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = statement_4_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEMICOLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // expr?
  private static boolean statement_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "statement_4_0")) return false;
    expr(builder_, level_ + 1, -1);
    return true;
  }

  /* ********************************************************** */
  // DOT_SYM symbol EQ_SYM expr
  public static boolean structLiteralField(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "structLiteralField")) return false;
    if (!nextTokenIs(builder_, DOT_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, DOT_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, EQ_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, STRUCT_LITERAL_FIELD, result_);
    return result_;
  }

  /* ********************************************************** */
  // SUSPEND_KEYWORD (SEP_SYM symbol SEP_SYM block)?
  public static boolean suspendBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suspendBlock")) return false;
    if (!nextTokenIs(builder_, SUSPEND_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SUSPEND_KEYWORD);
    result_ = result_ && suspendBlock_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, SUSPEND_BLOCK, result_);
    return result_;
  }

  // (SEP_SYM symbol SEP_SYM block)?
  private static boolean suspendBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suspendBlock_1")) return false;
    suspendBlock_1_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM symbol SEP_SYM block
  private static boolean suspendBlock_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suspendBlock_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // SUSPEND_KEYWORD (SEP_SYM symbol SEP_SYM exprOrBlock)?
  public static boolean suspendExprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suspendExprOrBlock")) return false;
    if (!nextTokenIs(builder_, SUSPEND_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SUSPEND_KEYWORD);
    result_ = result_ && suspendExprOrBlock_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, SUSPEND_EXPR_OR_BLOCK, result_);
    return result_;
  }

  // (SEP_SYM symbol SEP_SYM exprOrBlock)?
  private static boolean suspendExprOrBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suspendExprOrBlock_1")) return false;
    suspendExprOrBlock_1_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM symbol SEP_SYM exprOrBlock
  private static boolean suspendExprOrBlock_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suspendExprOrBlock_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // expr (RANGE_SYM expr)?
  public static boolean switchItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SWITCH_ITEM, "<switch item>");
    result_ = expr(builder_, level_ + 1, -1);
    result_ = result_ && switchItem_1(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (RANGE_SYM expr)?
  private static boolean switchItem_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchItem_1")) return false;
    switchItem_1_0(builder_, level_ + 1);
    return true;
  }

  // RANGE_SYM expr
  private static boolean switchItem_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchItem_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, RANGE_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (ELSE_KEYWORD | (switchItem (COMMA_SYM switchItem)*)?)
  //  ARROW_SYM
  //  (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //  expr COMMA_SYM
  public static boolean switchProng(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, SWITCH_PRONG, "<switch prong>");
    result_ = switchProng_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, ARROW_SYM);
    result_ = result_ && switchProng_2(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, COMMA_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ELSE_KEYWORD | (switchItem (COMMA_SYM switchItem)*)?
  private static boolean switchProng_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    if (!result_) result_ = switchProng_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (switchItem (COMMA_SYM switchItem)*)?
  private static boolean switchProng_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_0_1")) return false;
    switchProng_0_1_0(builder_, level_ + 1);
    return true;
  }

  // switchItem (COMMA_SYM switchItem)*
  private static boolean switchProng_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = switchItem(builder_, level_ + 1);
    result_ = result_ && switchProng_0_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM switchItem)*
  private static boolean switchProng_0_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_0_1_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!switchProng_0_1_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "switchProng_0_1_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM switchItem
  private static boolean switchProng_0_1_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_0_1_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMMA_SYM);
    result_ = result_ && switchItem(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean switchProng_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_2")) return false;
    switchProng_2_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean switchProng_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && switchProng_2_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean switchProng_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchProng_2_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  /* ********************************************************** */
  // SWITCH_KEYWORD
  //   LEFT_PAREN expr RIGHT_PAREN
  //   LEFT_BRACE switchProng* RIGHT_BRACE
  public static boolean switchStatement(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchStatement")) return false;
    if (!nextTokenIs(builder_, SWITCH_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, SWITCH_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeTokens(builder_, 0, RIGHT_PAREN, LEFT_BRACE);
    result_ = result_ && switchStatement_5(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, SWITCH_STATEMENT, result_);
    return result_;
  }

  // switchProng*
  private static boolean switchStatement_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "switchStatement_5")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!switchProng(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "switchStatement_5", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // IF_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //  (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //   block
  //  (ELSE_KEYWORD blockBlock)?
  public static boolean testBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testBlock")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && testBlock_4(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && testBlock_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, TEST_BLOCK, result_);
    return result_;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean testBlock_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testBlock_4")) return false;
    testBlock_4_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean testBlock_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testBlock_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && testBlock_4_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean testBlock_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testBlock_4_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  // (ELSE_KEYWORD blockBlock)?
  private static boolean testBlock_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testBlock_6")) return false;
    testBlock_6_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD blockBlock
  private static boolean testBlock_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testBlock_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && blockBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // TEST_KEYWORD string block
  public static boolean testDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testDeclaration")) return false;
    if (!nextTokenIs(builder_, TEST_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, TEST_KEYWORD);
    result_ = result_ && string(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, TEST_DECLARATION, result_);
    return result_;
  }

  /* ********************************************************** */
  // IF_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //  (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //   exprOrBlock
  //  (ELSE_KEYWORD blockExpr)?
  public static boolean testExprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testExprOrBlock")) return false;
    if (!nextTokenIs(builder_, IF_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, IF_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && testExprOrBlock_4(builder_, level_ + 1);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    result_ = result_ && testExprOrBlock_6(builder_, level_ + 1);
    exit_section_(builder_, marker_, TEST_EXPR_OR_BLOCK, result_);
    return result_;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean testExprOrBlock_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testExprOrBlock_4")) return false;
    testExprOrBlock_4_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean testExprOrBlock_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testExprOrBlock_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && testExprOrBlock_4_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean testExprOrBlock_4_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testExprOrBlock_4_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  // (ELSE_KEYWORD blockExpr)?
  private static boolean testExprOrBlock_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testExprOrBlock_6")) return false;
    testExprOrBlock_6_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD blockExpr
  private static boolean testExprOrBlock_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "testExprOrBlock_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && blockExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // COMPTIME_KEYWORD block
  //  | fnDeclaration
  //  | externDeclaration
  //  | globalVarDeclaration
  //  | useDeclaration
  //  | testDeclaration
  //  | expr
  static boolean topLevelItem(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "topLevelItem")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = topLevelItem_0(builder_, level_ + 1);
    if (!result_) result_ = fnDeclaration(builder_, level_ + 1);
    if (!result_) result_ = externDeclaration(builder_, level_ + 1);
    if (!result_) result_ = globalVarDeclaration(builder_, level_ + 1);
    if (!result_) result_ = useDeclaration(builder_, level_ + 1);
    if (!result_) result_ = testDeclaration(builder_, level_ + 1);
    if (!result_) result_ = expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // COMPTIME_KEYWORD block
  private static boolean topLevelItem_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "topLevelItem_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COMPTIME_KEYWORD);
    result_ = result_ && block(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // CATCH_KEYWORD (SEP_SYM symbol SEP_SYM)? expr
  public static boolean unwrapError(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unwrapError")) return false;
    if (!nextTokenIs(builder_, CATCH_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, CATCH_KEYWORD);
    result_ = result_ && unwrapError_1(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, UNWRAP_ERROR, result_);
    return result_;
  }

  // (SEP_SYM symbol SEP_SYM)?
  private static boolean unwrapError_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unwrapError_1")) return false;
    unwrapError_1_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM symbol SEP_SYM
  private static boolean unwrapError_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unwrapError_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // VERY_QUESTION_SYM expr
  public static boolean unwrapNullable(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unwrapNullable")) return false;
    if (!nextTokenIs(builder_, VERY_QUESTION_SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, VERY_QUESTION_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, UNWRAP_NULLABLE, result_);
    return result_;
  }

  /* ********************************************************** */
  // PUB_KEYWORD? USE_KEYWORD expr
  //  SEMICOLON_SYM
  public static boolean useDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "useDeclaration")) return false;
    if (!nextTokenIs(builder_, "<use declaration>", PUB_KEYWORD, USE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, USE_DECLARATION, "<use declaration>");
    result_ = useDeclaration_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, USE_KEYWORD);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, SEMICOLON_SYM);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // PUB_KEYWORD?
  private static boolean useDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "useDeclaration_0")) return false;
    consumeToken(builder_, PUB_KEYWORD);
    return true;
  }

  /* ********************************************************** */
  // (VAR_KEYWORD | CONST_KEYWORD) symbol (COLON_SYM (typeExpr | primary))?
  //  (ALIGN_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  //  (SECTION_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  //  (EQ_SYM expr)?
  public static boolean variableDeclaration(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration")) return false;
    if (!nextTokenIs(builder_, "<variable declaration>", CONST_KEYWORD, VAR_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, VARIABLE_DECLARATION, "<variable declaration>");
    result_ = variableDeclaration_0(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && variableDeclaration_2(builder_, level_ + 1);
    result_ = result_ && variableDeclaration_3(builder_, level_ + 1);
    result_ = result_ && variableDeclaration_4(builder_, level_ + 1);
    result_ = result_ && variableDeclaration_5(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // VAR_KEYWORD | CONST_KEYWORD
  private static boolean variableDeclaration_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_0")) return false;
    boolean result_;
    result_ = consumeToken(builder_, VAR_KEYWORD);
    if (!result_) result_ = consumeToken(builder_, CONST_KEYWORD);
    return result_;
  }

  // (COLON_SYM (typeExpr | primary))?
  private static boolean variableDeclaration_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_2")) return false;
    variableDeclaration_2_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM (typeExpr | primary)
  private static boolean variableDeclaration_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, COLON_SYM);
    result_ = result_ && variableDeclaration_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // typeExpr | primary
  private static boolean variableDeclaration_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_2_0_1")) return false;
    boolean result_;
    result_ = expr(builder_, level_ + 1, 18);
    if (!result_) result_ = expr(builder_, level_ + 1, 22);
    return result_;
  }

  // (ALIGN_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean variableDeclaration_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_3")) return false;
    variableDeclaration_3_0(builder_, level_ + 1);
    return true;
  }

  // ALIGN_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  private static boolean variableDeclaration_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, ALIGN_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (SECTION_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean variableDeclaration_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_4")) return false;
    variableDeclaration_4_0(builder_, level_ + 1);
    return true;
  }

  // SECTION_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  private static boolean variableDeclaration_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, SECTION_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (EQ_SYM expr)?
  private static boolean variableDeclaration_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_5")) return false;
    variableDeclaration_5_0(builder_, level_ + 1);
    return true;
  }

  // EQ_SYM expr
  private static boolean variableDeclaration_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "variableDeclaration_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, EQ_SYM);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (symbol COLON_SYM)? INLINE_KEYWORD?
  //  WHILE_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //   (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //   (COLON_SYM LEFT_PAREN expr RIGHT_PAREN)?
  //    block
  //   (ELSE_KEYWORD (SEP_SYM symbol SEP_SYM)? blockBlock)?
  public static boolean whileBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, WHILE_BLOCK, "<while block>");
    result_ = whileBlock_0(builder_, level_ + 1);
    result_ = result_ && whileBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, WHILE_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && whileBlock_6(builder_, level_ + 1);
    result_ = result_ && whileBlock_7(builder_, level_ + 1);
    result_ = result_ && block(builder_, level_ + 1);
    result_ = result_ && whileBlock_9(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (symbol COLON_SYM)?
  private static boolean whileBlock_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_0")) return false;
    whileBlock_0_0(builder_, level_ + 1);
    return true;
  }

  // symbol COLON_SYM
  private static boolean whileBlock_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // INLINE_KEYWORD?
  private static boolean whileBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_1")) return false;
    consumeToken(builder_, INLINE_KEYWORD);
    return true;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean whileBlock_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_6")) return false;
    whileBlock_6_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean whileBlock_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && whileBlock_6_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean whileBlock_6_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_6_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  // (COLON_SYM LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean whileBlock_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_7")) return false;
    whileBlock_7_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM LEFT_PAREN expr RIGHT_PAREN
  private static boolean whileBlock_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_7_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COLON_SYM, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ELSE_KEYWORD (SEP_SYM symbol SEP_SYM)? blockBlock)?
  private static boolean whileBlock_9(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_9")) return false;
    whileBlock_9_0(builder_, level_ + 1);
    return true;
  }

  // ELSE_KEYWORD (SEP_SYM symbol SEP_SYM)? blockBlock
  private static boolean whileBlock_9_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_9_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, ELSE_KEYWORD);
    result_ = result_ && whileBlock_9_0_1(builder_, level_ + 1);
    result_ = result_ && blockBlock(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (SEP_SYM symbol SEP_SYM)?
  private static boolean whileBlock_9_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_9_0_1")) return false;
    whileBlock_9_0_1_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM symbol SEP_SYM
  private static boolean whileBlock_9_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileBlock_9_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // (symbol COLON_SYM)? INLINE_KEYWORD?
  //  WHILE_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  //   (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  //   (COLON_SYM LEFT_PAREN expr RIGHT_PAREN)?
  //    exprOrBlock
  public static boolean whileExprOrBlock(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, WHILE_EXPR_OR_BLOCK, "<while expr or block>");
    result_ = whileExprOrBlock_0(builder_, level_ + 1);
    result_ = result_ && whileExprOrBlock_1(builder_, level_ + 1);
    result_ = result_ && consumeTokens(builder_, 0, WHILE_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    result_ = result_ && whileExprOrBlock_6(builder_, level_ + 1);
    result_ = result_ && whileExprOrBlock_7(builder_, level_ + 1);
    result_ = result_ && exprOrBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (symbol COLON_SYM)?
  private static boolean whileExprOrBlock_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_0")) return false;
    whileExprOrBlock_0_0(builder_, level_ + 1);
    return true;
  }

  // symbol COLON_SYM
  private static boolean whileExprOrBlock_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // INLINE_KEYWORD?
  private static boolean whileExprOrBlock_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_1")) return false;
    consumeToken(builder_, INLINE_KEYWORD);
    return true;
  }

  // (SEP_SYM STAR_SYM? symbol SEP_SYM)?
  private static boolean whileExprOrBlock_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_6")) return false;
    whileExprOrBlock_6_0(builder_, level_ + 1);
    return true;
  }

  // SEP_SYM STAR_SYM? symbol SEP_SYM
  private static boolean whileExprOrBlock_6_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_6_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeToken(builder_, SEP_SYM);
    result_ = result_ && whileExprOrBlock_6_0_1(builder_, level_ + 1);
    result_ = result_ && symbol(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, SEP_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STAR_SYM?
  private static boolean whileExprOrBlock_6_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_6_0_1")) return false;
    consumeToken(builder_, STAR_SYM);
    return true;
  }

  // (COLON_SYM LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean whileExprOrBlock_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_7")) return false;
    whileExprOrBlock_7_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM LEFT_PAREN expr RIGHT_PAREN
  private static boolean whileExprOrBlock_7_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "whileExprOrBlock_7_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokens(builder_, 0, COLON_SYM, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  /* ********************************************************** */
  // topLevelItem*
  static boolean zigFile(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "zigFile")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!topLevelItem(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "zigFile", pos_)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // Expression root: expr
  // Operator priority table:
  // 0: PREFIX(tryExpr)
  // 1: ATOM(returnExpr)
  // 2: ATOM(breakExpr)
  // 3: BINARY(assignExpr)
  // 4: PREFIX(cancelExpr)
  // 5: PREFIX(resumeExpr)
  // 6: PREFIX(awaitExpr)
  // 7: POSTFIX(unwrapExpr)
  // 8: BINARY(boolOrExpr)
  // 9: BINARY(boolAndExpr)
  // 10: BINARY(comparisonExpr)
  // 11: BINARY(orElseExpr)
  // 12: BINARY(binaryOrExpr)
  // 13: BINARY(binaryXorExpr)
  // 14: BINARY(binaryAndExpr)
  // 15: BINARY(bitShiftExpr)
  // 16: BINARY(additionExpr)
  // 17: BINARY(multiplyExpr)
  // 18: POSTFIX(curlySuffixExpr)
  // 19: BINARY(typeExpr)
  // 20: PREFIX(prefixOpExpr)
  // 21: POSTFIX(suffixOpExpr)
  // 22: ATOM(asyncExpr)
  // 23: ATOM(integer) ATOM(float) ATOM(string) ATOM(char)
  //    ATOM(keywordLiteral) ATOM(boolean) ATOM(null) ATOM(blockExpr)
  //    ATOM(symbol) ATOM(macroExpr) ATOM(arrayExpr) ATOM(fnProto)
  //    PREFIX(asmExpr) ATOM(containerExpr) ATOM(continueExpr) ATOM(errorSetExpr)
  //    PREFIX(groupedExpr)
  public static boolean expr(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "expr")) return false;
    addVariant(builder_, "<expr>");
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, "<expr>");
    result_ = tryExpr(builder_, level_ + 1);
    if (!result_) result_ = returnExpr(builder_, level_ + 1);
    if (!result_) result_ = breakExpr(builder_, level_ + 1);
    if (!result_) result_ = cancelExpr(builder_, level_ + 1);
    if (!result_) result_ = resumeExpr(builder_, level_ + 1);
    if (!result_) result_ = awaitExpr(builder_, level_ + 1);
    if (!result_) result_ = prefixOpExpr(builder_, level_ + 1);
    if (!result_) result_ = asyncExpr(builder_, level_ + 1);
    if (!result_) result_ = integer(builder_, level_ + 1);
    if (!result_) result_ = float_$(builder_, level_ + 1);
    if (!result_) result_ = string(builder_, level_ + 1);
    if (!result_) result_ = char_$(builder_, level_ + 1);
    if (!result_) result_ = keywordLiteral(builder_, level_ + 1);
    if (!result_) result_ = boolean_$(builder_, level_ + 1);
    if (!result_) result_ = null_$(builder_, level_ + 1);
    if (!result_) result_ = blockExpr(builder_, level_ + 1);
    if (!result_) result_ = symbol(builder_, level_ + 1);
    if (!result_) result_ = macroExpr(builder_, level_ + 1);
    if (!result_) result_ = arrayExpr(builder_, level_ + 1);
    if (!result_) result_ = fnProto(builder_, level_ + 1);
    if (!result_) result_ = asmExpr(builder_, level_ + 1);
    if (!result_) result_ = containerExpr(builder_, level_ + 1);
    if (!result_) result_ = continueExpr(builder_, level_ + 1);
    if (!result_) result_ = errorSetExpr(builder_, level_ + 1);
    if (!result_) result_ = groupedExpr(builder_, level_ + 1);
    pinned_ = result_;
    result_ = result_ && expr_0(builder_, level_ + 1, priority_);
    exit_section_(builder_, level_, marker_, null, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean expr_0(PsiBuilder builder_, int level_, int priority_) {
    if (!recursion_guard_(builder_, level_, "expr_0")) return false;
    boolean result_ = true;
    while (true) {
      Marker marker_ = enter_section_(builder_, level_, _LEFT_, null);
      if (priority_ < 3 && assignOperator(builder_, level_ + 1)) {
        result_ = expr(builder_, level_, 3);
        exit_section_(builder_, level_, marker_, ASSIGN_EXPR, result_, true, null);
      }
      else if (priority_ < 7 && unwrapExpr_0(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, UNWRAP_EXPR, result_, true, null);
      }
      else if (priority_ < 8 && consumeTokenSmart(builder_, OR_KEYWORD)) {
        result_ = expr(builder_, level_, 8);
        exit_section_(builder_, level_, marker_, BOOL_OR_EXPR, result_, true, null);
      }
      else if (priority_ < 9 && consumeTokenSmart(builder_, AND_KEYWORD)) {
        result_ = expr(builder_, level_, 9);
        exit_section_(builder_, level_, marker_, BOOL_AND_EXPR, result_, true, null);
      }
      else if (priority_ < 10 && comparisonOperator(builder_, level_ + 1)) {
        result_ = expr(builder_, level_, 10);
        exit_section_(builder_, level_, marker_, COMPARISON_EXPR, result_, true, null);
      }
      else if (priority_ < 11 && consumeTokenSmart(builder_, ORELSE_KEYWORD)) {
        result_ = expr(builder_, level_, 11);
        exit_section_(builder_, level_, marker_, OR_ELSE_EXPR, result_, true, null);
      }
      else if (priority_ < 12 && consumeTokenSmart(builder_, SEP_SYM)) {
        result_ = expr(builder_, level_, 12);
        exit_section_(builder_, level_, marker_, BINARY_OR_EXPR, result_, true, null);
      }
      else if (priority_ < 13 && consumeTokenSmart(builder_, EXPONENT_SYM)) {
        result_ = expr(builder_, level_, 13);
        exit_section_(builder_, level_, marker_, BINARY_XOR_EXPR, result_, true, null);
      }
      else if (priority_ < 14 && consumeTokenSmart(builder_, AND_SYM)) {
        result_ = expr(builder_, level_, 14);
        exit_section_(builder_, level_, marker_, BINARY_AND_EXPR, result_, true, null);
      }
      else if (priority_ < 15 && bitShiftOperator(builder_, level_ + 1)) {
        result_ = expr(builder_, level_, 15);
        exit_section_(builder_, level_, marker_, BIT_SHIFT_EXPR, result_, true, null);
      }
      else if (priority_ < 16 && additionOperator(builder_, level_ + 1)) {
        result_ = expr(builder_, level_, 16);
        exit_section_(builder_, level_, marker_, ADDITION_EXPR, result_, true, null);
      }
      else if (priority_ < 17 && multiplyOperator(builder_, level_ + 1)) {
        result_ = expr(builder_, level_, 17);
        exit_section_(builder_, level_, marker_, MULTIPLY_EXPR, result_, true, null);
      }
      else if (priority_ < 18 && containerInitSuffix(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, CURLY_SUFFIX_EXPR, result_, true, null);
      }
      else if (priority_ < 19 && consumeTokenSmart(builder_, NOT_SYM)) {
        result_ = expr(builder_, level_, 19);
        exit_section_(builder_, level_, marker_, TYPE_EXPR, result_, true, null);
      }
      else if (priority_ < 21 && suffixOpExpr_0(builder_, level_ + 1)) {
        result_ = true;
        exit_section_(builder_, level_, marker_, SUFFIX_OP_EXPR, result_, true, null);
      }
      else {
        exit_section_(builder_, level_, marker_, null, false, false, null);
        break;
      }
    }
    return result_;
  }

  public static boolean tryExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "tryExpr")) return false;
    if (!nextTokenIsSmart(builder_, TRY_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, TRY_KEYWORD);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 0);
    exit_section_(builder_, level_, marker_, TRY_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // RETURN_KEYWORD expr?
  public static boolean returnExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "returnExpr")) return false;
    if (!nextTokenIsSmart(builder_, RETURN_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, RETURN_KEYWORD);
    result_ = result_ && returnExpr_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, RETURN_EXPR, result_);
    return result_;
  }

  // expr?
  private static boolean returnExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "returnExpr_1")) return false;
    expr(builder_, level_ + 1, -1);
    return true;
  }

  // BREAK_KEYWORD (COLON_SYM symbol)? expr?
  public static boolean breakExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "breakExpr")) return false;
    if (!nextTokenIsSmart(builder_, BREAK_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, BREAK_KEYWORD);
    result_ = result_ && breakExpr_1(builder_, level_ + 1);
    result_ = result_ && breakExpr_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, BREAK_EXPR, result_);
    return result_;
  }

  // (COLON_SYM symbol)?
  private static boolean breakExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "breakExpr_1")) return false;
    breakExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM symbol
  private static boolean breakExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "breakExpr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, COLON_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // expr?
  private static boolean breakExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "breakExpr_2")) return false;
    expr(builder_, level_ + 1, -1);
    return true;
  }

  public static boolean cancelExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "cancelExpr")) return false;
    if (!nextTokenIsSmart(builder_, CANCEL_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, CANCEL_KEYWORD);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 4);
    exit_section_(builder_, level_, marker_, CANCEL_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean resumeExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "resumeExpr")) return false;
    if (!nextTokenIsSmart(builder_, RESUME_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, RESUME_KEYWORD);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 5);
    exit_section_(builder_, level_, marker_, RESUME_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  public static boolean awaitExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "awaitExpr")) return false;
    if (!nextTokenIsSmart(builder_, AWAIT_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, AWAIT_KEYWORD);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 6);
    exit_section_(builder_, level_, marker_, AWAIT_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // unwrapNullable | unwrapError
  private static boolean unwrapExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "unwrapExpr_0")) return false;
    boolean result_;
    result_ = unwrapNullable(builder_, level_ + 1);
    if (!result_) result_ = unwrapError(builder_, level_ + 1);
    return result_;
  }

  public static boolean prefixOpExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "prefixOpExpr")) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = prefixOp(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 20);
    exit_section_(builder_, level_, marker_, PREFIX_OP_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // fnCallSuffix
  //  | arrayAccessSuffix
  //  | fieldAccessSuffix
  //  | sliceSuffix
  //  | pointerSuffix
  //  | optionalSuffix
  private static boolean suffixOpExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "suffixOpExpr_0")) return false;
    boolean result_;
    result_ = fnCallSuffix(builder_, level_ + 1);
    if (!result_) result_ = arrayAccessSuffix(builder_, level_ + 1);
    if (!result_) result_ = fieldAccessSuffix(builder_, level_ + 1);
    if (!result_) result_ = sliceSuffix(builder_, level_ + 1);
    if (!result_) result_ = pointerSuffix(builder_, level_ + 1);
    if (!result_) result_ = optionalSuffix(builder_, level_ + 1);
    return result_;
  }

  // ASYNC_KEYWORD (LT_SYM primary GT_SYM)? primary fnCallSuffix
  public static boolean asyncExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asyncExpr")) return false;
    if (!nextTokenIsSmart(builder_, ASYNC_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, ASYNC_KEYWORD);
    result_ = result_ && asyncExpr_1(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, 22);
    result_ = result_ && fnCallSuffix(builder_, level_ + 1);
    exit_section_(builder_, marker_, ASYNC_EXPR, result_);
    return result_;
  }

  // (LT_SYM primary GT_SYM)?
  private static boolean asyncExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asyncExpr_1")) return false;
    asyncExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // LT_SYM primary GT_SYM
  private static boolean asyncExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asyncExpr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LT_SYM);
    result_ = result_ && expr(builder_, level_ + 1, 22);
    result_ = result_ && consumeToken(builder_, GT_SYM);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // INT_LITERAL
  public static boolean integer(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "integer")) return false;
    if (!nextTokenIsSmart(builder_, INT_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, INT_LITERAL);
    exit_section_(builder_, marker_, INTEGER, result_);
    return result_;
  }

  // FLOAT_LITERAL
  public static boolean float_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "float_$")) return false;
    if (!nextTokenIsSmart(builder_, FLOAT_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, FLOAT_LITERAL);
    exit_section_(builder_, marker_, FLOAT, result_);
    return result_;
  }

  // STR | RAW_STR
  public static boolean string(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "string")) return false;
    if (!nextTokenIsSmart(builder_, RAW_STR, STR)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, STRING, "<string>");
    result_ = consumeTokenSmart(builder_, STR);
    if (!result_) result_ = consumeTokenSmart(builder_, RAW_STR);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // CHAR_LITERAL
  public static boolean char_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "char_$")) return false;
    if (!nextTokenIsSmart(builder_, CHAR_LITERAL)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, CHAR_LITERAL);
    exit_section_(builder_, marker_, CHAR, result_);
    return result_;
  }

  // UNDEFINED_KEYWORD
  //  | ERROR_KEYWORD
  //  | THIS_KEYWORD
  //  | UNREACHABLE_KEYWORD
  //  | SUSPEND_KEYWORD
  public static boolean keywordLiteral(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "keywordLiteral")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, KEYWORD_LITERAL, "<keyword literal>");
    result_ = consumeTokenSmart(builder_, UNDEFINED_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, ERROR_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, THIS_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, UNREACHABLE_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, SUSPEND_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // TRUE_KEYWORD | FALSE_KEYWORD
  public static boolean boolean_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "boolean_$")) return false;
    if (!nextTokenIsSmart(builder_, FALSE_KEYWORD, TRUE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BOOLEAN, "<boolean $>");
    result_ = consumeTokenSmart(builder_, TRUE_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, FALSE_KEYWORD);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // NULL_KEYWORD
  public static boolean null_$(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "null_$")) return false;
    if (!nextTokenIsSmart(builder_, NULL_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, NULL_KEYWORD);
    exit_section_(builder_, marker_, NULL, result_);
    return result_;
  }

  // block
  //  | ifExprOrBlock
  //  | ifErrorExprOrBlock
  //  | testExprOrBlock
  //  | whileExprOrBlock
  //  | forOrBlock
  //  | switchStatement
  //  | compTimeExprOrBlock
  //  | suspendExprOrBlock
  public static boolean blockExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "blockExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, BLOCK_EXPR, "<block expr>");
    result_ = block(builder_, level_ + 1);
    if (!result_) result_ = ifExprOrBlock(builder_, level_ + 1);
    if (!result_) result_ = ifErrorExprOrBlock(builder_, level_ + 1);
    if (!result_) result_ = testExprOrBlock(builder_, level_ + 1);
    if (!result_) result_ = whileExprOrBlock(builder_, level_ + 1);
    if (!result_) result_ = forOrBlock(builder_, level_ + 1);
    if (!result_) result_ = switchStatement(builder_, level_ + 1);
    if (!result_) result_ = compTimeExprOrBlock(builder_, level_ + 1);
    if (!result_) result_ = suspendExprOrBlock(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // SYM
  public static boolean symbol(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "symbol")) return false;
    if (!nextTokenIsSmart(builder_, SYM)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, SYM);
    exit_section_(builder_, marker_, SYMBOL, result_);
    return result_;
  }

  // AT_SYM BUILTIN_FUNCTION fnCallSuffix
  public static boolean macroExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "macroExpr")) return false;
    if (!nextTokenIsSmart(builder_, AT_SYM)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, MACRO_EXPR, null);
    result_ = consumeTokensSmart(builder_, 1, AT_SYM, BUILTIN_FUNCTION);
    pinned_ = result_; // pin = 1
    result_ = result_ && fnCallSuffix(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, pinned_, null);
    return result_ || pinned_;
  }

  // LEFT_BRACKET expr? RIGHT_BRACKET
  //  (ALIGN_KEYWORD LEFT_PAREN expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN)?
  //  CONST_KEYWORD? VOLATILE_KEYWORD? typeExpr
  public static boolean arrayExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr")) return false;
    if (!nextTokenIsSmart(builder_, LEFT_BRACKET)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LEFT_BRACKET);
    result_ = result_ && arrayExpr_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACKET);
    result_ = result_ && arrayExpr_3(builder_, level_ + 1);
    result_ = result_ && arrayExpr_4(builder_, level_ + 1);
    result_ = result_ && arrayExpr_5(builder_, level_ + 1);
    result_ = result_ && expr(builder_, level_ + 1, 18);
    exit_section_(builder_, marker_, ARRAY_EXPR, result_);
    return result_;
  }

  // expr?
  private static boolean arrayExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_1")) return false;
    expr(builder_, level_ + 1, -1);
    return true;
  }

  // (ALIGN_KEYWORD LEFT_PAREN expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN)?
  private static boolean arrayExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_3")) return false;
    arrayExpr_3_0(builder_, level_ + 1);
    return true;
  }

  // ALIGN_KEYWORD LEFT_PAREN expr (COLON_SYM integer COLON_SYM integer)? RIGHT_PAREN
  private static boolean arrayExpr_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokensSmart(builder_, 0, ALIGN_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && arrayExpr_3_0_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COLON_SYM integer COLON_SYM integer)?
  private static boolean arrayExpr_3_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_3_0_3")) return false;
    arrayExpr_3_0_3_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM integer COLON_SYM integer
  private static boolean arrayExpr_3_0_3_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_3_0_3_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, COLON_SYM);
    result_ = result_ && integer(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, COLON_SYM);
    result_ = result_ && integer(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // CONST_KEYWORD?
  private static boolean arrayExpr_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_4")) return false;
    consumeTokenSmart(builder_, CONST_KEYWORD);
    return true;
  }

  // VOLATILE_KEYWORD?
  private static boolean arrayExpr_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "arrayExpr_5")) return false;
    consumeTokenSmart(builder_, VOLATILE_KEYWORD);
    return true;
  }

  // ( NAKEDCC_KEYWORD
  //  | STDCALLCC_KEYWORD
  //  | EXTERN_KEYWORD
  //  | (ASYNC_KEYWORD (LEFT_PAREN expr RIGHT_PAREN)?)
  //  )?
  //  FN_KEYWORD symbol? parameterList (ALIGN_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  //  (SECTION_KEYWORD LEFT_PAREN expr RIGHT_PAREN)? NOT_SYM? (typeExpr | VAR_KEYWORD)
  public static boolean fnProto(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, FN_PROTO, "<fn proto>");
    result_ = fnProto_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, FN_KEYWORD);
    result_ = result_ && fnProto_2(builder_, level_ + 1);
    result_ = result_ && parameterList(builder_, level_ + 1);
    result_ = result_ && fnProto_4(builder_, level_ + 1);
    result_ = result_ && fnProto_5(builder_, level_ + 1);
    result_ = result_ && fnProto_6(builder_, level_ + 1);
    result_ = result_ && fnProto_7(builder_, level_ + 1);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // ( NAKEDCC_KEYWORD
  //  | STDCALLCC_KEYWORD
  //  | EXTERN_KEYWORD
  //  | (ASYNC_KEYWORD (LEFT_PAREN expr RIGHT_PAREN)?)
  //  )?
  private static boolean fnProto_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_0")) return false;
    fnProto_0_0(builder_, level_ + 1);
    return true;
  }

  // NAKEDCC_KEYWORD
  //  | STDCALLCC_KEYWORD
  //  | EXTERN_KEYWORD
  //  | (ASYNC_KEYWORD (LEFT_PAREN expr RIGHT_PAREN)?)
  private static boolean fnProto_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, NAKEDCC_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, STDCALLCC_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, EXTERN_KEYWORD);
    if (!result_) result_ = fnProto_0_0_3(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ASYNC_KEYWORD (LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean fnProto_0_0_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_0_0_3")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, ASYNC_KEYWORD);
    result_ = result_ && fnProto_0_0_3_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean fnProto_0_0_3_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_0_0_3_1")) return false;
    fnProto_0_0_3_1_0(builder_, level_ + 1);
    return true;
  }

  // LEFT_PAREN expr RIGHT_PAREN
  private static boolean fnProto_0_0_3_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_0_0_3_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // symbol?
  private static boolean fnProto_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_2")) return false;
    symbol(builder_, level_ + 1);
    return true;
  }

  // (ALIGN_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean fnProto_4(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_4")) return false;
    fnProto_4_0(builder_, level_ + 1);
    return true;
  }

  // ALIGN_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  private static boolean fnProto_4_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_4_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokensSmart(builder_, 0, ALIGN_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (SECTION_KEYWORD LEFT_PAREN expr RIGHT_PAREN)?
  private static boolean fnProto_5(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_5")) return false;
    fnProto_5_0(builder_, level_ + 1);
    return true;
  }

  // SECTION_KEYWORD LEFT_PAREN expr RIGHT_PAREN
  private static boolean fnProto_5_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_5_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokensSmart(builder_, 0, SECTION_KEYWORD, LEFT_PAREN);
    result_ = result_ && expr(builder_, level_ + 1, -1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // NOT_SYM?
  private static boolean fnProto_6(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_6")) return false;
    consumeTokenSmart(builder_, NOT_SYM);
    return true;
  }

  // typeExpr | VAR_KEYWORD
  private static boolean fnProto_7(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "fnProto_7")) return false;
    boolean result_;
    result_ = expr(builder_, level_ + 1, 18);
    if (!result_) result_ = consumeTokenSmart(builder_, VAR_KEYWORD);
    return result_;
  }

  public static boolean asmExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmExpr")) return false;
    if (!nextTokenIsSmart(builder_, ASM_KEYWORD)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = asmExpr_0(builder_, level_ + 1);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, 22);
    result_ = pinned_ && report_error_(builder_, asmExpr_1(builder_, level_ + 1)) && result_;
    exit_section_(builder_, level_, marker_, ASM_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

  // ASM_KEYWORD VOLATILE_KEYWORD? LEFT_PAREN
  private static boolean asmExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmExpr_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, ASM_KEYWORD);
    result_ = result_ && asmExpr_0_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // VOLATILE_KEYWORD?
  private static boolean asmExpr_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmExpr_0_1")) return false;
    consumeTokenSmart(builder_, VOLATILE_KEYWORD);
    return true;
  }

  // asmOutput? RIGHT_PAREN
  private static boolean asmExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmExpr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = asmExpr_1_0(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_PAREN);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // asmOutput?
  private static boolean asmExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "asmExpr_1_0")) return false;
    asmOutput(builder_, level_ + 1);
    return true;
  }

  // (EXTERN_KEYWORD | PACKED_KEYWORD)?
  //  ( STRUCT_KEYWORD groupedExpr?
  //  | UNION_KEYWORD (ENUM_KEYWORD groupedExpr? | groupedExpr)?
  //  | ENUM_KEYWORD groupedExpr?
  //  )
  //  LEFT_BRACE containerMember* RIGHT_BRACE
  public static boolean containerExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, CONTAINER_EXPR, "<container expr>");
    result_ = containerExpr_0(builder_, level_ + 1);
    result_ = result_ && containerExpr_1(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, LEFT_BRACE);
    result_ = result_ && containerExpr_3(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, level_, marker_, result_, false, null);
    return result_;
  }

  // (EXTERN_KEYWORD | PACKED_KEYWORD)?
  private static boolean containerExpr_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_0")) return false;
    containerExpr_0_0(builder_, level_ + 1);
    return true;
  }

  // EXTERN_KEYWORD | PACKED_KEYWORD
  private static boolean containerExpr_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_0_0")) return false;
    boolean result_;
    result_ = consumeTokenSmart(builder_, EXTERN_KEYWORD);
    if (!result_) result_ = consumeTokenSmart(builder_, PACKED_KEYWORD);
    return result_;
  }

  // STRUCT_KEYWORD groupedExpr?
  //  | UNION_KEYWORD (ENUM_KEYWORD groupedExpr? | groupedExpr)?
  //  | ENUM_KEYWORD groupedExpr?
  private static boolean containerExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = containerExpr_1_0(builder_, level_ + 1);
    if (!result_) result_ = containerExpr_1_1(builder_, level_ + 1);
    if (!result_) result_ = containerExpr_1_2(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // STRUCT_KEYWORD groupedExpr?
  private static boolean containerExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, STRUCT_KEYWORD);
    result_ = result_ && containerExpr_1_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // groupedExpr?
  private static boolean containerExpr_1_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_0_1")) return false;
    groupedExpr(builder_, level_ + 1);
    return true;
  }

  // UNION_KEYWORD (ENUM_KEYWORD groupedExpr? | groupedExpr)?
  private static boolean containerExpr_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_1")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, UNION_KEYWORD);
    result_ = result_ && containerExpr_1_1_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (ENUM_KEYWORD groupedExpr? | groupedExpr)?
  private static boolean containerExpr_1_1_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_1_1")) return false;
    containerExpr_1_1_1_0(builder_, level_ + 1);
    return true;
  }

  // ENUM_KEYWORD groupedExpr? | groupedExpr
  private static boolean containerExpr_1_1_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_1_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = containerExpr_1_1_1_0_0(builder_, level_ + 1);
    if (!result_) result_ = groupedExpr(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ENUM_KEYWORD groupedExpr?
  private static boolean containerExpr_1_1_1_0_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_1_1_0_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, ENUM_KEYWORD);
    result_ = result_ && containerExpr_1_1_1_0_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // groupedExpr?
  private static boolean containerExpr_1_1_1_0_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_1_1_0_0_1")) return false;
    groupedExpr(builder_, level_ + 1);
    return true;
  }

  // ENUM_KEYWORD groupedExpr?
  private static boolean containerExpr_1_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_2")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, ENUM_KEYWORD);
    result_ = result_ && containerExpr_1_2_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // groupedExpr?
  private static boolean containerExpr_1_2_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_1_2_1")) return false;
    groupedExpr(builder_, level_ + 1);
    return true;
  }

  // containerMember*
  private static boolean containerExpr_3(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "containerExpr_3")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!containerMember(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "containerExpr_3", pos_)) break;
    }
    return true;
  }

  // CONTINUE_KEYWORD (COLON_SYM symbol)?
  public static boolean continueExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "continueExpr")) return false;
    if (!nextTokenIsSmart(builder_, CONTINUE_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, CONTINUE_KEYWORD);
    result_ = result_ && continueExpr_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, CONTINUE_EXPR, result_);
    return result_;
  }

  // (COLON_SYM symbol)?
  private static boolean continueExpr_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "continueExpr_1")) return false;
    continueExpr_1_0(builder_, level_ + 1);
    return true;
  }

  // COLON_SYM symbol
  private static boolean continueExpr_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "continueExpr_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, COLON_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // ERROR_KEYWORD
  //  LEFT_BRACE (symbol (COMMA_SYM symbol)*)? RIGHT_BRACE
  public static boolean errorSetExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "errorSetExpr")) return false;
    if (!nextTokenIsSmart(builder_, ERROR_KEYWORD)) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokensSmart(builder_, 0, ERROR_KEYWORD, LEFT_BRACE);
    result_ = result_ && errorSetExpr_2(builder_, level_ + 1);
    result_ = result_ && consumeToken(builder_, RIGHT_BRACE);
    exit_section_(builder_, marker_, ERROR_SET_EXPR, result_);
    return result_;
  }

  // (symbol (COMMA_SYM symbol)*)?
  private static boolean errorSetExpr_2(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "errorSetExpr_2")) return false;
    errorSetExpr_2_0(builder_, level_ + 1);
    return true;
  }

  // symbol (COMMA_SYM symbol)*
  private static boolean errorSetExpr_2_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "errorSetExpr_2_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = symbol(builder_, level_ + 1);
    result_ = result_ && errorSetExpr_2_0_1(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  // (COMMA_SYM symbol)*
  private static boolean errorSetExpr_2_0_1(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "errorSetExpr_2_0_1")) return false;
    while (true) {
      int pos_ = current_position_(builder_);
      if (!errorSetExpr_2_0_1_0(builder_, level_ + 1)) break;
      if (!empty_element_parsed_guard_(builder_, "errorSetExpr_2_0_1", pos_)) break;
    }
    return true;
  }

  // COMMA_SYM symbol
  private static boolean errorSetExpr_2_0_1_0(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "errorSetExpr_2_0_1_0")) return false;
    boolean result_;
    Marker marker_ = enter_section_(builder_);
    result_ = consumeTokenSmart(builder_, COMMA_SYM);
    result_ = result_ && symbol(builder_, level_ + 1);
    exit_section_(builder_, marker_, null, result_);
    return result_;
  }

  public static boolean groupedExpr(PsiBuilder builder_, int level_) {
    if (!recursion_guard_(builder_, level_, "groupedExpr")) return false;
    if (!nextTokenIsSmart(builder_, LEFT_PAREN)) return false;
    boolean result_, pinned_;
    Marker marker_ = enter_section_(builder_, level_, _NONE_, null);
    result_ = consumeTokenSmart(builder_, LEFT_PAREN);
    pinned_ = result_;
    result_ = pinned_ && expr(builder_, level_, -1);
    result_ = pinned_ && report_error_(builder_, consumeToken(builder_, RIGHT_PAREN)) && result_;
    exit_section_(builder_, level_, marker_, GROUPED_EXPR, result_, pinned_, null);
    return result_ || pinned_;
  }

}

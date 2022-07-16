// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigComparisonExpr extends ZigExpr {

  @NotNull
  ZigComparisonOperator getComparisonOperator();

  @NotNull
  List<ZigExpr> getExprList();

}

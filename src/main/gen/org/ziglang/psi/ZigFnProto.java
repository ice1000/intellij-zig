// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNameIdentifierOwner;

public interface ZigFnProto extends ZigExpr, PsiNameIdentifierOwner {

  @NotNull
  List<ZigExpr> getExprList();

  @NotNull
  ZigParameterList getParameterList();

}

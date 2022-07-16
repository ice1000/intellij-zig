// This is a generated file. Not intended for manual editing.
package org.ziglang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.ziglang.psi.ZigTypes.*;
import org.ziglang.psi.*;

public class ZigNullImpl extends ZigExprImpl implements ZigNull {

  public ZigNullImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitNull(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

}
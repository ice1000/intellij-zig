// This is a generated file. Not intended for manual editing.
package org.ziglang.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static org.ziglang.psi.ZigTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import org.ziglang.psi.*;

public class ZigStatementImpl extends ASTWrapperPsiElement implements ZigStatement {

  public ZigStatementImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitStatement(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigBlockBlock getBlockBlock() {
    return findChildByClass(ZigBlockBlock.class);
  }

  @Override
  @Nullable
  public ZigDefer getDefer() {
    return findChildByClass(ZigDefer.class);
  }

  @Override
  @Nullable
  public ZigDeferBlock getDeferBlock() {
    return findChildByClass(ZigDeferBlock.class);
  }

  @Override
  @Nullable
  public ZigExpr getExpr() {
    return findChildByClass(ZigExpr.class);
  }

  @Override
  @Nullable
  public ZigLocalVariableDeclaration getLocalVariableDeclaration() {
    return findChildByClass(ZigLocalVariableDeclaration.class);
  }

}

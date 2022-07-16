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

public class ZigBlockExprImpl extends ZigExprImpl implements ZigBlockExpr {

  public ZigBlockExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitBlockExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigBlock getBlock() {
    return findChildByClass(ZigBlock.class);
  }

  @Override
  @Nullable
  public ZigCompTimeExprOrBlock getCompTimeExprOrBlock() {
    return findChildByClass(ZigCompTimeExprOrBlock.class);
  }

  @Override
  @Nullable
  public ZigForOrBlock getForOrBlock() {
    return findChildByClass(ZigForOrBlock.class);
  }

  @Override
  @Nullable
  public ZigIfErrorExprOrBlock getIfErrorExprOrBlock() {
    return findChildByClass(ZigIfErrorExprOrBlock.class);
  }

  @Override
  @Nullable
  public ZigIfExprOrBlock getIfExprOrBlock() {
    return findChildByClass(ZigIfExprOrBlock.class);
  }

  @Override
  @Nullable
  public ZigSuspendExprOrBlock getSuspendExprOrBlock() {
    return findChildByClass(ZigSuspendExprOrBlock.class);
  }

  @Override
  @Nullable
  public ZigSwitchStatement getSwitchStatement() {
    return findChildByClass(ZigSwitchStatement.class);
  }

  @Override
  @Nullable
  public ZigTestExprOrBlock getTestExprOrBlock() {
    return findChildByClass(ZigTestExprOrBlock.class);
  }

  @Override
  @Nullable
  public ZigWhileExprOrBlock getWhileExprOrBlock() {
    return findChildByClass(ZigWhileExprOrBlock.class);
  }

}

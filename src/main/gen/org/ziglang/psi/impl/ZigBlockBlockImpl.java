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

public class ZigBlockBlockImpl extends ASTWrapperPsiElement implements ZigBlockBlock {

  public ZigBlockBlockImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitBlockBlock(this);
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
  public ZigCompTimeBlock getCompTimeBlock() {
    return findChildByClass(ZigCompTimeBlock.class);
  }

  @Override
  @Nullable
  public ZigForBlock getForBlock() {
    return findChildByClass(ZigForBlock.class);
  }

  @Override
  @Nullable
  public ZigIfBlock getIfBlock() {
    return findChildByClass(ZigIfBlock.class);
  }

  @Override
  @Nullable
  public ZigIfErrorBlock getIfErrorBlock() {
    return findChildByClass(ZigIfErrorBlock.class);
  }

  @Override
  @Nullable
  public ZigSuspendBlock getSuspendBlock() {
    return findChildByClass(ZigSuspendBlock.class);
  }

  @Override
  @Nullable
  public ZigSwitchStatement getSwitchStatement() {
    return findChildByClass(ZigSwitchStatement.class);
  }

  @Override
  @Nullable
  public ZigTestBlock getTestBlock() {
    return findChildByClass(ZigTestBlock.class);
  }

  @Override
  @Nullable
  public ZigWhileBlock getWhileBlock() {
    return findChildByClass(ZigWhileBlock.class);
  }

}

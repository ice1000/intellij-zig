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

public class ZigSuffixOpExprImpl extends ZigExprImpl implements ZigSuffixOpExpr {

  public ZigSuffixOpExprImpl(@NotNull ASTNode node) {
    super(node);
  }

  @Override
  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitSuffixOpExpr(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigArrayAccessSuffix getArrayAccessSuffix() {
    return findChildByClass(ZigArrayAccessSuffix.class);
  }

  @Override
  @NotNull
  public ZigExpr getExpr() {
    return findNotNullChildByClass(ZigExpr.class);
  }

  @Override
  @Nullable
  public ZigFieldAccessSuffix getFieldAccessSuffix() {
    return findChildByClass(ZigFieldAccessSuffix.class);
  }

  @Override
  @Nullable
  public ZigFnCallSuffix getFnCallSuffix() {
    return findChildByClass(ZigFnCallSuffix.class);
  }

  @Override
  @Nullable
  public ZigOptionalSuffix getOptionalSuffix() {
    return findChildByClass(ZigOptionalSuffix.class);
  }

  @Override
  @Nullable
  public ZigPointerSuffix getPointerSuffix() {
    return findChildByClass(ZigPointerSuffix.class);
  }

  @Override
  @Nullable
  public ZigSliceSuffix getSliceSuffix() {
    return findChildByClass(ZigSliceSuffix.class);
  }

}

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

public class ZigAsmOutputImpl extends ASTWrapperPsiElement implements ZigAsmOutput {

  public ZigAsmOutputImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitAsmOutput(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigAsmInput getAsmInput() {
    return findChildByClass(ZigAsmInput.class);
  }

  @Override
  @NotNull
  public List<ZigAsmOutputItem> getAsmOutputItemList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, ZigAsmOutputItem.class);
  }

}

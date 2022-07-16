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

public class ZigContainerMemberImpl extends ASTWrapperPsiElement implements ZigContainerMember {

  public ZigContainerMemberImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull ZigVisitor visitor) {
    visitor.visitContainerMember(this);
  }

  @Override
  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof ZigVisitor) accept((ZigVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @Nullable
  public ZigContainerField getContainerField() {
    return findChildByClass(ZigContainerField.class);
  }

  @Override
  @Nullable
  public ZigFnDeclaration getFnDeclaration() {
    return findChildByClass(ZigFnDeclaration.class);
  }

  @Override
  @Nullable
  public ZigGlobalVarDeclaration getGlobalVarDeclaration() {
    return findChildByClass(ZigGlobalVarDeclaration.class);
  }

}

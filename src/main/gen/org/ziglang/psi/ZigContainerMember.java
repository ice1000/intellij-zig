// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigContainerMember extends PsiElement {

  @Nullable
  ZigContainerField getContainerField();

  @Nullable
  ZigFnDeclaration getFnDeclaration();

  @Nullable
  ZigGlobalVarDeclaration getGlobalVarDeclaration();

}

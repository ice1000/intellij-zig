// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigStatement extends PsiElement {

  @Nullable
  ZigBlockBlock getBlockBlock();

  @Nullable
  ZigDefer getDefer();

  @Nullable
  ZigDeferBlock getDeferBlock();

  @Nullable
  ZigExpr getExpr();

  @Nullable
  ZigLocalVariableDeclaration getLocalVariableDeclaration();

}

// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigBlockExpr extends ZigExpr {

  @Nullable
  ZigBlock getBlock();

  @Nullable
  ZigCompTimeExprOrBlock getCompTimeExprOrBlock();

  @Nullable
  ZigForOrBlock getForOrBlock();

  @Nullable
  ZigIfErrorExprOrBlock getIfErrorExprOrBlock();

  @Nullable
  ZigIfExprOrBlock getIfExprOrBlock();

  @Nullable
  ZigSuspendExprOrBlock getSuspendExprOrBlock();

  @Nullable
  ZigSwitchStatement getSwitchStatement();

  @Nullable
  ZigTestExprOrBlock getTestExprOrBlock();

  @Nullable
  ZigWhileExprOrBlock getWhileExprOrBlock();

}

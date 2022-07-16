// This is a generated file. Not intended for manual editing.
package org.ziglang.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface ZigBlockBlock extends PsiElement {

  @Nullable
  ZigBlock getBlock();

  @Nullable
  ZigCompTimeBlock getCompTimeBlock();

  @Nullable
  ZigForBlock getForBlock();

  @Nullable
  ZigIfBlock getIfBlock();

  @Nullable
  ZigIfErrorBlock getIfErrorBlock();

  @Nullable
  ZigSuspendBlock getSuspendBlock();

  @Nullable
  ZigSwitchStatement getSwitchStatement();

  @Nullable
  ZigTestBlock getTestBlock();

  @Nullable
  ZigWhileBlock getWhileBlock();

}

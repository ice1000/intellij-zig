package org.ziglang.execution;

import com.intellij.openapi.options.SettingsEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class ZigRunConfigurationEditor extends SettingsEditor<ZigRunConfiguration> {
	protected @NotNull JPanel mainPanel;
}

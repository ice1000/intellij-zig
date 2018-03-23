package org.ziglang.execution;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.ui.RawCommandLineEditor;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public abstract class ZigRunConfigurationEditor extends SettingsEditor<ZigRunConfiguration> {
	protected @NotNull JPanel mainPanel;
	protected @NotNull ComboboxWithBrowseButton executablePath;
	protected @NotNull TextFieldWithBrowseButton targetFileField;
	protected @NotNull TextFieldWithBrowseButton workingDirField;
	protected @NotNull RawCommandLineEditor compilerArgsField;
	protected @NotNull RawCommandLineEditor programArgsField;
	protected @NotNull TextFieldWithBrowseButton installPathField;
}

package org.ziglang.execution;

import com.intellij.openapi.options.SettingsEditor;
import com.intellij.openapi.ui.ComboBox;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.ui.RawCommandLineEditor;
import com.intellij.ui.components.JBCheckBox;
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
	protected @NotNull TextFieldWithBrowseButton outputDirField;
	protected @NotNull ComboBox<String> releaseMode;
	protected @NotNull ComboBox<String> coloredMode;
	protected @NotNull JBCheckBox statically;
	protected @NotNull JBCheckBox strip;
	protected @NotNull JBCheckBox verboseTokenize;
	protected @NotNull JBCheckBox verboseParsing;
	protected @NotNull JBCheckBox verboseLinking;
	protected @NotNull JBCheckBox verboseCImports;
	protected @NotNull JBCheckBox verboseZigIR;
	protected @NotNull JBCheckBox verboseLlvmIR;
}

package org.ziglang.project.ui;

import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.ui.ComboboxWithBrowseButton;
import com.intellij.ui.components.labels.LinkLabel;
import org.jetbrains.annotations.NotNull;

import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ZigConfigurable implements Configurable {
    protected @NotNull JPanel mainPanel;
    protected @NotNull ComboboxWithBrowseButton executablePath;
    protected @NotNull JLabel version;
    protected @NotNull TextFieldWithBrowseButton installPathField;
    protected @NotNull LinkLabel<Object> zigWebsite;
    protected @NotNull LinkLabel<Object> iceZigRelease;
}

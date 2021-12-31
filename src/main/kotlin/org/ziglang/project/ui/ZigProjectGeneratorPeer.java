package org.ziglang.project.ui;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.platform.ProjectGeneratorPeer;
import com.intellij.ui.components.labels.LinkLabel;
import org.jetbrains.annotations.NotNull;
import org.ziglang.project.ZigSettings;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public abstract class ZigProjectGeneratorPeer implements ProjectGeneratorPeer<ZigSettings> {
    protected @NotNull JPanel mainPanel;
    protected @NotNull com.intellij.ui.ComboboxWithBrowseButton executablePath;
    protected @NotNull LinkLabel<Object> zigWebsite;
    protected @NotNull JLabel version;
    protected @NotNull JCheckBox setupLater;
    protected @NotNull TextFieldWithBrowseButton installPathField;
    protected @NotNull LinkLabel<Object> iceZigRelease;
}

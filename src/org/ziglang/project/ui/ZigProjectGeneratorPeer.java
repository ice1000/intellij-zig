package org.ziglang.project.ui;

import com.intellij.platform.ProjectGeneratorPeer;
import com.intellij.ui.components.labels.LinkLabel;
import org.jetbrains.annotations.NotNull;
import org.ziglang.project.ZigSettings;

import javax.swing.*;

public abstract class ZigProjectGeneratorPeer implements ProjectGeneratorPeer<ZigSettings> {
	protected @NotNull JPanel mainPanel;
	protected @NotNull com.intellij.ui.ComboboxWithBrowseButton executablePath;
	protected @NotNull LinkLabel<Object> zigWebsite;
	protected @NotNull JLabel version;
	protected @NotNull JCheckBox setupLater;
}

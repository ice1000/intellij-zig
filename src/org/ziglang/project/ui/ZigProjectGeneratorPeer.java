package org.ziglang.project.ui;

import com.intellij.openapi.ui.TextFieldWithBrowseButton;
import com.intellij.platform.ProjectGeneratorPeer;
import org.jetbrains.annotations.NotNull;
import org.ziglang.project.ZigSettings;

import javax.swing.*;

public abstract class ZigProjectGeneratorPeer implements ProjectGeneratorPeer<ZigSettings> {
	protected @NotNull JPanel mainPanel;
	protected @NotNull
	TextFieldWithBrowseButton executablePath;
}

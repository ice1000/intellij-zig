package org.ziglang.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.ui.Messages.showDialog
import com.petebevin.markdown.MarkdownProcessor
import icons.ZigIcons
import org.ziglang.ZigBundle
import org.ziglang.executeCommand
import org.ziglang.project.validateZigExe
import org.ziglang.project.zigSettings
import com.google.common.io.Files as GoogleFiles

class ZigZenAction : AnAction(
		ZigBundle.message("zig.actions.zen.title"),
		ZigBundle.message("zig.actions.zen.description"),
		ZigIcons.ZIG_WEBSITE_ICON) {
	override fun update(e: AnActionEvent) {
		e.project?.run { e.presentation.isEnabledAndVisible = validateZigExe(zigSettings.settings.exePath) }
		super.update(e)
	}

	override fun actionPerformed(e: AnActionEvent) {
		val project = e.project ?: return
		val zig = project.zigSettings.settings.exePath
		val (stdout) = executeCommand(arrayOf(zig, "zen"))
		showDialog(project,
				"<html>${MarkdownProcessor().markdown(stdout.joinToString("\n"))}</html>",
				ZigBundle.message("zig.actions.zen.dialog.title"),
				arrayOf(ZigBundle.message("zig.yes")),
				0,
				null)
	}
}

package org.ziglang.action

import com.intellij.openapi.actionSystem.AnActionEvent
import org.ziglang.ZigBundle

class ZigBuildAction : ZigAction(
	ZigBundle.message("zig.actions.build.title"),
	ZigBundle.message("zig.actions.build.description")
) {
	override fun actionPerformed(e: AnActionEvent?) {
		//TODO do something when `build` button click
	}
}
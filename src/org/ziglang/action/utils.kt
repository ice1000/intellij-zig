package org.ziglang.action

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import org.ziglang.ZigFileType

/**
 * Copy from julia-intellij (>_<)
 */
abstract class ZigAction(
	text: String?,
	description: String?) :
	AnAction(text, description, null) {
	protected fun fileType(e: AnActionEvent) = e.getData(CommonDataKeys.VIRTUAL_FILE)?.fileType == ZigFileType
	override fun update(e: AnActionEvent) {
		e.presentation.isEnabledAndVisible = fileType(e)
	}
}
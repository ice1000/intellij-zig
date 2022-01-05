package org.ziglang.action

import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.progress.Task
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.vfs.VfsUtil
import org.ziglang.ZIG_EXTENSION
import org.ziglang.i18n.ZigBundle
import org.ziglang.util.executeCommand
import org.ziglang.icons.ZigIcons
import org.ziglang.project.validateZigExe
import org.ziglang.project.zigSettings

class ZigTranslateFromCAction : AnAction(
    ZigBundle.message("zig.actions.c-translate.title"),
    ZigBundle.message("zig.actions.c-translate.description"),
    ZigIcons.ZIG_WEBSITE_ICON
) {
    override fun actionPerformed(e: AnActionEvent) {
        val project = e.project ?: return
        val zigSettings = project.zigSettings.settings
        val cFile = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext) ?: return
        val zigFileName = "${cFile.nameWithoutExtension}.$ZIG_EXTENSION"
        val (stdout, stderr) = ProgressManager.getInstance().run(object :
            Task.WithResult<Pair<List<String>, List<String>>, Exception>(
                project, "", false
            ) {
            override fun compute(indicator: ProgressIndicator) = executeCommand(
                arrayOf(
                    zigSettings.exePath,
                    "translate-c",
                    cFile.path
                ), timeLimit = 10000L
            )
        })
        ApplicationManager.getApplication().runWriteAction {
            if (stderr.isNotEmpty() && stderr.all(String::isNotEmpty)) Messages.showErrorDialog(
                project,
                stderr.joinToString("\n"),
                ZigBundle.message("zig.actions.c-translate.error.title")
            )
            if (stdout.isNotEmpty() && stdout.all(String::isNotEmpty)) {
                val zigFile = cFile.parent.findOrCreateChildData(this, zigFileName)
                VfsUtil.saveText(zigFile, stdout.joinToString("\n"))
                OpenFileDescriptor(project, zigFile).navigate(true)
            }
        }
    }

    override fun update(e: AnActionEvent) {
        e.project?.run {
            e.presentation.run {
                val file = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext)
                val fileType = file?.fileType as? LanguageFileType
                isVisible = validateZigExe(zigSettings.settings.exePath)
                val clang = Language.findLanguageByID("C")
                isEnabled = file != null &&
                        (fileType != null && clang != null && fileType.language == clang || file.extension == "c")
            }
        }
        super.update(e)
    }
}
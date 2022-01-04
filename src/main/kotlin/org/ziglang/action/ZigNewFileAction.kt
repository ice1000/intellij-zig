package org.ziglang.action

import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.actions.AttributesDefaults
import com.intellij.ide.fileTemplates.ui.CreateFromTemplateDialog
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.io.FileUtilRt
import com.intellij.psi.PsiDirectory
import org.ziglang.ZigBundle
import org.ziglang.editing.ZigNameValidator
import org.ziglang.icons.ZigIcons
import org.ziglang.project.zigSettings

class ZigNewFileAction : CreateFileFromTemplateAction(
    ZigBundle.message("zig.actions.new-file.title"),
    ZigBundle.message("zig.actions.new-file.description"),
    ZigIcons.ZIG_FILE
), DumbAware {
    companion object PropertyCreator {
        fun createProperties(project: Project, fileName: String) =
            FileTemplateManager.getInstance(project).defaultProperties.also { properties ->
                properties += "ZIG_VERSION" to project.zigSettings.settings.version
                properties += "NAME" to fileName
            }
    }

    override fun buildDialog(project: Project, directory: PsiDirectory, builder: CreateFileFromTemplateDialog.Builder) {
        builder
            .setTitle(ZigBundle.message("zig.actions.new-file.title"))
            .setValidator(ZigNameValidator)
            .addKind("File", ZigIcons.ZIG_FILE, "Zig File")
            .addKind("Executable", ZigIcons.ZIG_FILE, "Zig Exe")
        //.addKind("Other", ZigIcons.ZIG_BIG_ICON, "Zig Other")		For test
    }

    override fun getActionName(dir: PsiDirectory, name: String, templateName: String) =
        ZigBundle.message("zig.actions.new-file.title")

    override fun createFileFromTemplate(name: String, template: FileTemplate, dir: PsiDirectory) = try {
        val fileName = FileUtilRt.getNameWithoutExtension(name)
        val project = dir.project
        val properties = createProperties(project, fileName)
        CreateFromTemplateDialog(project, dir, template, AttributesDefaults(fileName).withFixedName(true), properties)
            .create()
            .containingFile
    } catch (e: Exception) {
        LOG.error("Error while creating new file", e)
        null
    }
}

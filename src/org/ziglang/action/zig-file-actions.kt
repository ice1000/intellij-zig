package org.ziglang.action

import com.google.common.io.Files
import com.intellij.execution.*
import com.intellij.execution.executors.DefaultRunExecutor
import com.intellij.ide.actions.CreateFileFromTemplateAction
import com.intellij.ide.actions.CreateFileFromTemplateDialog
import com.intellij.ide.fileTemplates.FileTemplate
import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.actions.AttributesDefaults
import com.intellij.ide.fileTemplates.ui.CreateFromTemplateDialog
import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.progress.*
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.util.io.FileUtilRt
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiDirectory
import icons.ZigIcons
import org.ziglang.*
import org.ziglang.editing.ZigNameValidator
import org.ziglang.execution.ZigRunConfiguration
import org.ziglang.execution.ZigRunConfigurationType
import org.ziglang.project.validateZigExe
import org.ziglang.project.zigSettings

class ZigTranslateFromCAction : AnAction(
		ZigBundle.message("zig.actions.c-translate.title"),
		ZigBundle.message("zig.actions.c-translate.description"),
		ZigIcons.ZIG_WEBSITE_ICON) {
	override fun actionPerformed(e: AnActionEvent) {
		val project = e.project ?: return
		val zigSettings = project.zigSettings.settings
		val cFile = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext) ?: return
		val zigFileName = "${cFile.nameWithoutExtension}.$ZIG_EXTENSION"
		val (stdout, stderr) = ProgressManager.getInstance().run(object :
				Task.WithResult<Pair<List<String>, List<String>>, Exception>(
						project, "", false) {
			override fun compute(indicator: ProgressIndicator) = executeCommand(arrayOf(
					zigSettings.exePath,
					"translate-c",
					cFile.path,
					ZIG_INSTALL_PREFIX,
					zigSettings.installPath
			), timeLimit = 10000L)
		})
		ApplicationManager.getApplication().runWriteAction {
			if (stderr.isNotEmpty() && stderr.all(String::isNotEmpty)) Messages.showErrorDialog(
					project,
					stderr.joinToString("\n"),
					ZigBundle.message("zig.actions.c-translate.error.title"))
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

class ZigBuildAction : ZigAction(
		ZigBundle.message("zig.actions.build.title"),
		ZigBundle.message("zig.actions.build.description")) {
	override fun actionPerformed(e: AnActionEvent) {
		val project = e.project ?: return
		val file = CommonDataKeys.VIRTUAL_FILE.getData(e.dataContext) ?: return
		val path = file.path.trimPath()
		val executor = ExecutorRegistry.getInstance().getExecutorById(DefaultRunExecutor.EXECUTOR_ID)
		val configuration = RunManager
				.getInstance(project)
				.let {
					it.createRunConfiguration(
							"build${Files.getNameWithoutExtension(path)}",
							ZigRunConfigurationType.configurationFactories[0]
					).apply {
						it.addConfiguration(this)
						(configuration as ZigRunConfiguration).apply {
							targetFile = path
							isBuildOnly = true
						}
					}

				}
		ProgramRunnerUtil.executeConfiguration(configuration, executor)
	}
}

class NewZigFile : CreateFileFromTemplateAction(
		ZigBundle.message("zig.actions.new-file.title"),
		ZigBundle.message("zig.actions.new-file.description"),
		ZigIcons.ZIG_FILE), DumbAware {
	companion object PropertyCreator {
		fun createProperties(project: Project, fileName: String) =
				FileTemplateManager.getInstance(project).defaultProperties.also { properties ->
					properties += "ZIG_VERSION" to project.zigSettings.settings.version
					properties += "NAME" to fileName
				}
	}

	override fun getActionName(dir: PsiDirectory?, name: String?, templateName: String?) =
			ZigBundle.message("zig.actions.new-file.title")

	override fun buildDialog(project: Project?, dir: PsiDirectory?, builder: CreateFileFromTemplateDialog.Builder) {
		builder
				.setTitle(ZigBundle.message("zig.actions.new-file.title"))
				.setValidator(ZigNameValidator)
				.addKind("File", ZigIcons.ZIG_FILE, "Zig File")
				.addKind("Executable", ZigIcons.ZIG_FILE, "Zig Exe")
		//.addKind("Other", ZigIcons.ZIG_BIG_ICON, "Zig Other")		For test
	}

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

package org.ziglang.execution

import com.intellij.execution.Executor
import com.intellij.execution.actions.ConfigurationContext
import com.intellij.execution.actions.RunConfigurationProducer
import com.intellij.execution.configurations.*
import com.intellij.execution.runners.ExecutionEnvironment
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.components.PathMacroManager
import com.intellij.openapi.project.DumbAware
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.JDOMExternalizer
import com.intellij.openapi.util.Ref
import com.intellij.psi.PsiElement
import icons.ZigIcons
import org.jdom.Element
import org.ziglang.*
import org.ziglang.project.validateZigExe
import org.ziglang.project.zigSettings
import java.nio.file.Paths
import com.google.common.io.Files as GoogleFiles

class ZigRunConfiguration(project: Project, factory: ConfigurationFactory) :
		LocatableConfigurationBase(project, factory, ZigBundle.message("zig.name")), DumbAware {
	var exePath = ""
	var targetFile = ""
	var workingDir = ""
	var additionalOptions = ""
	var programArgs = ""
	var installPath = ""
	var outputDir = ""

	override fun getState(executor: Executor, environment: ExecutionEnvironment) =
			ZigCommandLineState(this@ZigRunConfiguration, environment)

	override fun getIcon() = ZigIcons.ZIG_BIG_ICON
	override fun getConfigurationEditor() = ZigRunConfigurationEditorImpl(this)
	override fun readExternal(element: Element) {
		super.readExternal(element)
		PathMacroManager.getInstance(project).expandPaths(element)
		JDOMExternalizer.readString(element, "exePath")?.let { exePath = it }
		JDOMExternalizer.readString(element, "targetFile")?.let { targetFile = it }
		JDOMExternalizer.readString(element, "workingDir")?.let { workingDir = it }
		JDOMExternalizer.readString(element, "additionalOptions")?.let { additionalOptions = it }
		JDOMExternalizer.readString(element, "programArgs")?.let { programArgs = it }
		JDOMExternalizer.readString(element, "installPath")?.let { installPath = it }
		JDOMExternalizer.readString(element, "outputDir")?.let { outputDir = it }
	}

	override fun writeExternal(element: Element) {
		super.writeExternal(element)
		JDOMExternalizer.write(element, "exePath", exePath)
		JDOMExternalizer.write(element, "targetFile", targetFile)
		JDOMExternalizer.write(element, "workingDir", workingDir)
		JDOMExternalizer.write(element, "additionalOptions", additionalOptions)
		JDOMExternalizer.write(element, "programArgs", programArgs)
		JDOMExternalizer.write(element, "installPath", installPath)
		JDOMExternalizer.write(element, "outputDir", outputDir)
		PathMacroManager.getInstance(project).collapsePathsRecursively(element)
	}
}

object ZigRunConfigurationType : ConfigurationType, DumbAware {
	private val factories = arrayOf(ZigRunConfigurationFactory(this))
	override fun getIcon() = ZigIcons.ZIG_WEBSITE_ICON
	override fun getConfigurationTypeDescription() = ZigBundle.message("zig.run-config.description")
	override fun getDisplayName() = ZigBundle.message("zig.name")
	override fun getConfigurationFactories() = factories
	override fun getId() = ZIG_RUN_CONFIG_ID
}

class ZigRunConfigurationFactory(type: ConfigurationType) : ConfigurationFactory(type), DumbAware {
	override fun createTemplateConfiguration(project: Project) = ZigRunConfiguration(project, this).apply {
		project.zigSettings.settings.let {
			exePath = it.exePath
			installPath = it.installPath
		}
		project.baseDir.run {
			workingDir = path
			outputDir = Paths.get(canonicalPath, "out").toString()
		}
	}
}

class ZigRunConfigurationProducer :
		RunConfigurationProducer<ZigRunConfiguration>(ZigRunConfigurationType) {
	override fun isConfigurationFromContext(
			configuration: ZigRunConfiguration, context: ConfigurationContext) =
			configuration.targetFile == CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.run { path.trimPath() }

	private fun validate(
			configuration: ZigRunConfiguration, context: ConfigurationContext) =
			CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.fileType == ZigFileType &&
					validateZigExe(configuration.exePath)

	override fun setupConfigurationFromContext(
			configuration: ZigRunConfiguration, context: ConfigurationContext, sourceElement: Ref<PsiElement>): Boolean {
		CommonDataKeys
				.VIRTUAL_FILE
				.getData(context.dataContext)
				?.path
				.orEmpty()
				.trimPath()
				.let {
					configuration.targetFile = it
					configuration.name = GoogleFiles.getNameWithoutExtension(configuration.targetFile)
				}
		return validate(configuration, context)
	}
}

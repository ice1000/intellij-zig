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

class ZigRunConfiguration(project: Project, factory: ConfigurationFactory) :
		LocatableConfigurationBase(project, factory, ZigBundle.message("zig.name")), DumbAware {
	var exePath = ""
	var filePath = ""
	var workingDir = ""
	var additionalOptions = ""
	var programArgs = ""

	override fun getState(executor: Executor, environment: ExecutionEnvironment) =
			ZigCommandLineState(this@ZigRunConfiguration, environment)

	override fun getIcon() = ZigIcons.ZIG_BIG_ICON
	override fun getConfigurationEditor() = ZigRunConfigurationEditorImpl(this)
	override fun readExternal(element: Element) {
		super.readExternal(element)
		PathMacroManager.getInstance(project).expandPaths(element)
		JDOMExternalizer.readString(element, "exePath")?.let { exePath = it }
		JDOMExternalizer.readString(element, "filePath")?.let { filePath = it }
		JDOMExternalizer.readString(element, "workingDir")?.let { workingDir = it }
		JDOMExternalizer.readString(element, "additionalOptions")?.let { additionalOptions = it }
		JDOMExternalizer.readString(element, "programArgs")?.let { programArgs = it }
	}

	override fun writeExternal(element: Element) {
		super.writeExternal(element)
		JDOMExternalizer.write(element, "exePath", exePath)
		JDOMExternalizer.write(element, "filePath", filePath)
		JDOMExternalizer.write(element, "workingDir", workingDir)
		JDOMExternalizer.write(element, "additionalOptions", additionalOptions)
		JDOMExternalizer.write(element, "programArgs", programArgs)
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
		exePath = project.zigSettings.settings.exePath
		workingDir = project.basePath.orEmpty()
	}
}

class ZigRunConfigurationProducer :
		RunConfigurationProducer<ZigRunConfiguration>(ZigRunConfigurationType) {
	override fun isConfigurationFromContext(
			configuration: ZigRunConfiguration, context: ConfigurationContext) =
			configuration.filePath == CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.run { path.trimPath() }

	private fun validate(
			configuration: ZigRunConfiguration, context: ConfigurationContext) =
			CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.fileType == ZigFileType &&
					validateZigExe(configuration.exePath)

	override fun setupConfigurationFromContext(
			configuration: ZigRunConfiguration, context: ConfigurationContext, sourceElement: Ref<PsiElement>): Boolean {
		configuration.filePath = CommonDataKeys.VIRTUAL_FILE.getData(context.dataContext)?.path.orEmpty().trimPath()
		return validate(configuration, context)
	}
}

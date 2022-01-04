package org.ziglang.project

import com.intellij.ide.fileTemplates.FileTemplateManager
import com.intellij.ide.fileTemplates.FileTemplateUtil
import com.intellij.ide.util.projectWizard.AbstractNewProjectStep
import com.intellij.ide.util.projectWizard.CustomStepProjectGenerator
import com.intellij.ide.util.projectWizard.ProjectSettingsStepBase
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.application.runWriteAction
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.rootManager
import com.intellij.openapi.roots.ModifiableModelsProvider
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.platform.DirectoryProjectGenerator
import com.intellij.platform.DirectoryProjectGeneratorBase
import com.intellij.psi.PsiManager
import com.intellij.util.PlatformUtils
import org.ziglang.ZigBundle
import org.ziglang.action.ZigNewFileAction
import org.ziglang.icons.ZigIcons
import org.ziglang.project.ui.ZigProjectGeneratorPeerImpl

class ZigProjectGenerator : DirectoryProjectGeneratorBase<ZigSettings>(),
    CustomStepProjectGenerator<ZigSettings> {
    override fun createStep(
        projectGenerator: DirectoryProjectGenerator<ZigSettings>,
        callback: AbstractNewProjectStep.AbstractCallback<ZigSettings>
    ) =
        ProjectSettingsStepBase(projectGenerator, AbstractNewProjectStep.AbstractCallback<ZigSettings>())

    override fun getLogo() = ZigIcons.ZIG_BIG_ICON
    override fun getName() = ZigBundle.message("zig.name")
    override fun createPeer() = ZigProjectGeneratorPeerImpl()

    override fun generateProject(project: Project, baseDir: VirtualFile, settings: ZigSettings, module: Module) {
        (project.zigSettings as ZigProjectServiceImpl).loadState(settings)
        ApplicationManager.getApplication().runWriteAction {
            val modifiableModel: ModifiableRootModel =
                ModifiableModelsProvider.SERVICE.getInstance().getModuleModifiableModel(module)
            module.rootManager.modifiableModel.apply {
                inheritSdk()
                contentEntries.firstOrNull()?.apply {
                    addExcludeFolder(findOrCreate(baseDir, "out", module))
                    addExcludeFolder(findOrCreate(baseDir, "zig-cache", module))
                    addSourceFolder(findOrCreate(baseDir, "src", module), false)
                }
                commit()
            }
            ModifiableModelsProvider.SERVICE.getInstance().commitModuleModifiableModel(modifiableModel)
            project.forCLion()
        }
    }

    /**
     * Codes for CLion, write zig information in CMakeLists.txt
     */
    private fun Project.forCLion() {
        if (PlatformUtils.isCLion()) {
            fun generateCMakeFile(baseDir: VirtualFile) = runWriteAction {
                val cmakeList = baseDir.findOrCreateChildData(this, "CMakeLists.txt")
                VfsUtil.saveText(
                    cmakeList, """
project($name)
"""
                )
            }

            val template = FileTemplateManager
                .getInstance(this)
                .getTemplate("Zig Exe")
            
            val srcDir = baseDir.createChildDirectory(null, "src")

            PsiManager.getInstance(this).findDirectory(srcDir)?.let {
                FileTemplateUtil.createFromTemplate(template, "main.zig", ZigNewFileAction.createProperties(this, "main"), it)
            }

            generateCMakeFile(baseDir)
        }
    }
}

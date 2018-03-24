package org.ziglang.project

import com.intellij.execution.configurations.PathEnvironmentVariableUtil
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory
import com.intellij.openapi.module.Module
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.rootManager
import com.intellij.openapi.ui.TextComponentAccessor
import com.intellij.openapi.util.SystemInfo
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.ui.ComboboxWithBrowseButton
import org.ziglang.ZigBundle
import org.ziglang.executeCommand
import java.nio.file.Files
import java.nio.file.Paths
import javax.swing.JComboBox

val zigPath: String by lazy {
	PathEnvironmentVariableUtil.findInPath("zig")?.absolutePath
			?: when {
				SystemInfo.isWindows -> "C://Program Files/"
				SystemInfo.isMac -> "C://Program Files/" // TODO
				else -> "/usr/bin/zig"
			}
}

fun versionOf(path: String) = executeCommand("$path version")
		.first
		.firstOrNull()
		?: ZigBundle.message("zig.version.unknown")

fun validateZigExe(exePath: String) = Files.isExecutable(Paths.get(exePath))

// https://github.com/zig-lang/zig/blob/7350181a4a778f9d03186e5123beffdf80f58606/src/main.cpp#L140-L173
fun validateZigLib(libPath: String) = Files.isReadable(Paths.get(libPath, "lib", "zig", "std", "index.zig"))

fun validateZigSDK(sdkHome: String) = Files.isExecutable(Paths.get(sdkHome, "bin", "zig")) or
		Files.isExecutable(Paths.get(sdkHome, "bin", "zig.exe"))

inline fun initExeComboBox(
		zigExeField: ComboboxWithBrowseButton,
		project: Project? = null,
		crossinline addListener: (ComboboxWithBrowseButton) -> Unit = {}) {
	zigExeField.addBrowseFolderListener(ZigBundle.message("zig.messages.run.select-compiler"),
			ZigBundle.message("zig.messages.run.select-compiler.description"),
			project,
			FileChooserDescriptorFactory.createSingleFileOrExecutableAppDescriptor(),
			object : TextComponentAccessor<JComboBox<Any>> {
				override fun getText(component: JComboBox<Any>) = component.selectedItem as? String ?: ""
				override fun setText(component: JComboBox<Any>, text: String) {
					component.addItem(text)
					addListener(zigExeField)
				}
			})
	zigGlobalSettings.knownZigExes.forEach(zigExeField.comboBox::addItem)
}

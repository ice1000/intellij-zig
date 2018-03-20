package org.ziglang.project

import com.intellij.execution.configurations.PathEnvironmentVariableUtil
import com.intellij.openapi.roots.ModifiableRootModel
import com.intellij.openapi.util.SystemInfo
import java.nio.file.*

val zigPath: String by lazy {
	PathEnvironmentVariableUtil.findInPath("zig")?.absolutePath
			?: when {
				SystemInfo.isWindows -> "C://Program Files/"
				SystemInfo.isMac -> "C://Program Files/" // TODO
				else -> "/usr/bin/zig"
			}
}

fun versionOf(path: String) = "" // TODO

fun validateJuliaSDK(sdkHome: String) = Files.isExecutable(Paths.get(sdkHome, "bin", "zig")) or
		Files.isExecutable(Paths.get(sdkHome, "bin", "zig.exe")) // TODO

fun createSourceDirectory(model: ModifiableRootModel, entryPath: String?): Path {
	model.inheritSdk()
	val srcPath = Paths.get(entryPath, "src").toAbsolutePath()
	Files.createDirectories(srcPath)
	return srcPath
}

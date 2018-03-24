package org.ziglang.execution

import com.intellij.execution.configurations.GeneralCommandLine
import com.intellij.execution.process.OSProcessHandler
import com.intellij.openapi.project.Project
import java.nio.file.Paths


fun Project.build(configuration: ZigRunConfiguration): OSProcessHandler {
	val buildParams = mutableListOf<String>()
	val outputFile = Paths.get(
		configuration.outputDir,
		configuration.name
	).toString()
	with(configuration) {
		buildParams += exePath
		buildParams += "build-exe"
		buildParams += targetFile
		buildParams += "--zig-install-prefix"
		buildParams += installPath
		buildParams += "--output"
		buildParams += outputFile
		buildParams += additionalOptions.split(' ', '\n').filter(String::isNotBlank)
	}
	return OSProcessHandler(GeneralCommandLine(buildParams)
		.withWorkDirectory(configuration.workingDir))
}
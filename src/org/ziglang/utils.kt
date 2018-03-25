package org.ziglang

import com.google.common.util.concurrent.SimpleTimeLimiter
import java.io.File
import java.util.concurrent.TimeUnit

fun Boolean?.orFalse() = this == true

fun String.trimPath() = trimEnd('/', '!')

/**
 * @param command the cmd string
 * @param input stdin
 * @param timeLimit maximum execution time
 */
fun executeCommand(
		command: List<String>,
		input: String? = null,
		timeLimit: Long = 1200L,
		workingDirectory: String? = null): Pair<List<String>, List<String>> {
	var processRef: Process? = null
	var output: List<String> = emptyList()
	var outputErr: List<String> = emptyList()
	try {
		SimpleTimeLimiter().callWithTimeout({
			val builder = ProcessBuilder()
			if (workingDirectory != null)
				builder.directory(File(workingDirectory))
			val process: Process = builder
					.command(command)
					.start()
			processRef = process
			process.outputStream.use {
				if (input != null) it.write(input.toByteArray())
				it.flush()
			}
			process.waitFor()
			output = process.inputStream.use { it.reader().useLines { it.toList() } }
			outputErr = process.errorStream.use { it.reader().useLines { it.toList() } }
			process.destroy()
		}, timeLimit + 100, TimeUnit.MILLISECONDS, true)
	} catch (e: Throwable) {
		processRef?.destroy()
	}
	return output to outputErr
}

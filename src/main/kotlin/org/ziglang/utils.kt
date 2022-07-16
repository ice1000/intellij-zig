package org.ziglang

import com.intellij.openapi.util.TextRange
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

fun Boolean?.orFalse() = this == true
fun Boolean?.orTrue() = this != false

fun String.trimPath() = trimEnd('/', '!')

/**
 * @param command the cmd string
 * @param input stdin
 * @param timeLimit maximum execution time
 */
fun executeCommand(
		command: Array<String>, input: String? = null, timeLimit: Long = 1200L): Pair<List<String>, List<String>> {
	var processRef: Process? = null
	var output: List<String> = emptyList()
	var outputErr: List<String> = emptyList()
	val executor = Executors.newCachedThreadPool()
	val future = executor.submit {
		val process: Process = Runtime.getRuntime().exec(command)
		processRef = process
		process.outputStream.use {
			if (input != null) it.write(input.toByteArray())
			it.flush()
		}
		process.waitFor()
		output = process.inputStream.use { it.reader().useLines { it.toList() } }
		outputErr = process.errorStream.use { it.reader().useLines { it.toList() } }
		process.destroy()
	}
	try {
		future.get(timeLimit, TimeUnit.MILLISECONDS)
	} catch (ignored: Throwable) {
		// timeout? catch it and give up anyway
	} finally {
		processRef?.destroy()
	}
	return output to outputErr
}

fun TextRange.subRange(start: Int, end: Int) = TextRange(startOffset + start, startOffset + end + 1)

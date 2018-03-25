package org.ziglang

import com.google.common.util.concurrent.SimpleTimeLimiter
import com.intellij.psi.PsiElement
import java.util.concurrent.TimeUnit

fun Boolean?.orFalse() = this == true

fun String.trimPath() = trimEnd('/', '!')

/**
 * @param command the cmd string
 * @param input stdin
 * @param timeLimit maximum execution time
 */
fun executeCommand(
		command: String, input: String? = null, timeLimit: Long = 1200L): Pair<List<String>, List<String>> {
	var processRef: Process? = null
	var output: List<String> = emptyList()
	var outputErr: List<String> = emptyList()
	try {
		SimpleTimeLimiter().callWithTimeout({
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
		}, timeLimit + 100, TimeUnit.MILLISECONDS, true)
	} catch (e: Throwable) {
		processRef?.destroy()
	}
	return output to outputErr
}

// stupid code
// TODO 改成 PsiTraverser
fun PsiElement.forEach(forBody: (PsiElement) -> Unit) {
	children.forEach {
		forBody(it)
		it.forEach(forBody)
	}
}
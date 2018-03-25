package org.ziglang.execution

import com.intellij.execution.filters.*
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope
import java.util.regex.Pattern

class ZigConsoleFilter(private val project: Project) : Filter {
	private companion object {
		private val ERROR_FILE_LOCATION = Pattern.compile("(.+\\.zig):([0-9]+):([0-9]+):")
	}

	override fun applyFilter(line: String, entireLength: Int): Filter.Result? {
		val startIndex = entireLength - line.length
		if (startIndex == 0) return null
		val matcher = ERROR_FILE_LOCATION.matcher(line)
		if (!matcher.lookingAt()) return null
		val resultFilePath = matcher.group(1)
		val resultFile = project.baseDir.fileSystem.findFileByPath(resultFilePath) ?: return null
		val lineNumber = matcher.group(2).toIntOrNull() ?: return null
		val columnNumber = matcher.group(3).toIntOrNull() ?: return null
		return Filter.Result(
				startIndex,
				startIndex + resultFilePath.length,
				OpenFileHyperlinkInfo(
						project,
						resultFile,
						(lineNumber - 1).coerceAtLeast(0),
						columnNumber
				)
		)
	}
}

class ZigConsoleFilterProvider : ConsoleFilterProviderEx {
	override fun getDefaultFilters(project: Project, scope: GlobalSearchScope) = getDefaultFilters(project)
	override fun getDefaultFilters(project: Project) = arrayOf(ZigConsoleFilter(project), UrlFilter())
}
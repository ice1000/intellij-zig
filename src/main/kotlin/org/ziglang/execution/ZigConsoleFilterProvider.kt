package org.ziglang.execution

import com.intellij.execution.filters.ConsoleFilterProviderEx
import com.intellij.execution.filters.UrlFilter
import com.intellij.openapi.project.Project
import com.intellij.psi.search.GlobalSearchScope

class ZigConsoleFilterProvider : ConsoleFilterProviderEx {
    override fun getDefaultFilters(project: Project, scope: GlobalSearchScope) = getDefaultFilters(project)

    override fun getDefaultFilters(project: Project) = arrayOf(ZigConsoleFilter(project), UrlFilter())
}
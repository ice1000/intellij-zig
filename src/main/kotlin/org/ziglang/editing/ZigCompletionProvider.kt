package org.ziglang.editing

import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.util.ProcessingContext

class ZigCompletionProvider(private val list: List<LookupElement>) : CompletionProvider<CompletionParameters>() {
    override fun addCompletions(
        parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet
    ) =
        list.forEach(result::addElement)
}
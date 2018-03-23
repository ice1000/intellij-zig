package org.ziglang.editing

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import org.ziglang.ZigSyntaxHighlighter
import org.ziglang.psi.ZigSymbol

class ZigAnnotator : Annotator {
	override fun annotate(element: PsiElement, holder: AnnotationHolder) {
		when (element) {
			is ZigSymbol -> symbol(element, holder)
		}
	}

	private fun symbol(element: PsiElement, holder: AnnotationHolder) {
		holder.createInfoAnnotation(element, null)
				.textAttributes = ZigSyntaxHighlighter.FUNCTION_DECLARATION
	}
}

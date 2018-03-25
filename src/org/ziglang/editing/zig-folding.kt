package org.ziglang.editing

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import org.ziglang.forEach
import org.ziglang.psi.ZigBlock
import org.ziglang.psi.ZigTypes

// TODO 移动到 editing 里面
class ZigFolderBuilder : FoldingBuilderEx(), DumbAware {
	private fun fold(element: PsiElement, holder: String) =
		object : FoldingDescriptor(element.node, element.textRange) {
			override fun getPlaceholderText() = holder
		}

	//树..树的遍历..?
	override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean) =
		ArrayList<FoldingDescriptor>().apply {
			root.forEach { element ->
				when (element) {
					is ZigBlock -> add(fold(element, "{...}"))
				}
			}
		}.toTypedArray()


	override fun isCollapsedByDefault(node: ASTNode) = true
	override fun getPlaceholderText(node: ASTNode) = "..."
}
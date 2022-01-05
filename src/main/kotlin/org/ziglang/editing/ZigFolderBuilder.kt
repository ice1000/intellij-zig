package org.ziglang.editing

import com.intellij.lang.ASTNode
import com.intellij.lang.folding.FoldingBuilderEx
import com.intellij.lang.folding.FoldingDescriptor
import com.intellij.openapi.editor.Document
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.PsiElement
import com.intellij.psi.SyntaxTraverser
import org.ziglang.psi.ZigBlock
import org.ziglang.psi.ZigErrorSetExpr

class ZigFolderBuilder : FoldingBuilderEx(), DumbAware {

    override fun buildFoldRegions(root: PsiElement, document: Document, quick: Boolean) =
        SyntaxTraverser.psiTraverser(root)
            .filter { it is ZigBlock && !it.textRange.isEmpty }
            .toList()
            .map { FoldingDescriptor(it.node, it.textRange, null, if (it is ZigErrorSetExpr) "error …" else "{…}") }
            .toTypedArray()

    override fun isCollapsedByDefault(node: ASTNode) = true
    override fun getPlaceholderText(node: ASTNode) = "…"
}
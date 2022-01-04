package org.ziglang.editing

import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

class ZigStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(file: PsiFile) = object : TreeBasedStructureViewBuilder() {
        override fun isRootNodeShown() = true
        override fun createStructureViewModel(editor: Editor?) = ZigStructureViewModel(file, editor)
    }
}
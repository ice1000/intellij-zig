package org.ziglang.editing

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile
import org.ziglang.psi.*

class ZigStructureViewModel(root: PsiFile, editor: Editor?) :
    StructureViewModelBase(root, editor, ZigStructureViewElement(root)),
    StructureViewModel.ElementInfoProvider {
    init {
        withSuitableClasses(
            ZigFnDeclaration::class.java,
            ZigExternDeclaration::class.java,
            ZigGlobalVarDeclaration::class.java,
            ZigUseDeclaration::class.java,
            ZigIfBlock::class.java,
            ZigIfExprOrBlock::class.java,
            ZigIfErrorBlock::class.java,
            ZigIfExprOrBlock::class.java,
            ZigTestBlock::class.java,
            ZigTestExprOrBlock::class.java
        )
    }

    override fun shouldEnterElement(element: Any?) = true
    override fun isAlwaysShowsPlus(element: StructureViewTreeElement?) = false
    override fun isAlwaysLeaf(element: StructureViewTreeElement?) = element is ZigFnDeclaration
}
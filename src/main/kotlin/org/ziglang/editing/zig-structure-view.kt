package org.ziglang.editing

import com.intellij.ide.structureView.StructureViewModel
import com.intellij.ide.structureView.StructureViewModelBase
import com.intellij.ide.structureView.StructureViewTreeElement
import com.intellij.ide.structureView.TreeBasedStructureViewBuilder
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.pom.Navigatable
import com.intellij.psi.NavigatablePsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiNamedElement
import icons.ZigIcons
import org.ziglang.ZigFile
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

class ZigStructureViewElement(private val root: NavigatablePsiElement) :
    StructureViewTreeElement, ItemPresentation, SortableTreeElement, Navigatable by root {
    override fun getLocationString() = ""
    override fun getIcon(open: Boolean) = when (root) {
        is ZigFile -> ZigIcons.ZIG_FILE
        is ZigFnDeclaration -> ZigIcons.ZIG_FUN
        is ZigGlobalVarDeclaration -> ZigIcons.ZIG_VAR
        else -> ZigIcons.ZIG_BIG_ICON
    }

    //返回显示的字符串吧, 60应该是字符上限
    override fun getPresentableText() = cutText(root.presentText(), 60)

    override fun getPresentation() = this
    override fun getValue() = root
    override fun getAlphaSortKey() = (root as? PsiNamedElement)?.name.orEmpty()
    override fun getChildren() = root
        .children
        .filter { it.treeViewTokens }
        .map { ZigStructureViewElement(it as NavigatablePsiElement) }
        .toTypedArray()
}

class ZigStructureViewFactory : PsiStructureViewFactory {
    override fun getStructureViewBuilder(file: PsiFile) = object : TreeBasedStructureViewBuilder() {
        override fun isRootNodeShown() = true
        override fun createStructureViewModel(editor: Editor?) = ZigStructureViewModel(file, editor)
    }
}
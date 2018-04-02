package org.ziglang.editing

import com.intellij.ide.structureView.*
import com.intellij.ide.util.treeView.smartTree.SortableTreeElement
import com.intellij.lang.PsiStructureViewFactory
import com.intellij.navigation.ItemPresentation
import com.intellij.openapi.editor.Editor
import com.intellij.pom.Navigatable
import com.intellij.psi.*
import icons.ZigIcons
import org.ziglang.ZigFile
import org.ziglang.psi.*

class ZigStructureViewModel(root: PsiFile, editor: Editor?) :
		StructureViewModelBase(root, editor, ZigStructureViewElement(root)),
		StructureViewModel.ElementInfoProvider {
	init {
		withSuitableClasses(
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
	override fun isAlwaysLeaf(element: StructureViewTreeElement?) = element is Any    //TODO replace `Any` with `ZigFunction`
}

class ZigStructureViewElement(private val root: NavigatablePsiElement) :
		StructureViewTreeElement, ItemPresentation, SortableTreeElement, Navigatable by root {
	override fun getLocationString() = ""
	// TODO 提供icon用的, 还得做图标呜呜呜
	override fun getIcon(open: Boolean) = when (root) {
		is ZigFile -> ZigIcons.ZIG_FILE
		else -> ZigIcons.ZIG_BIG_ICON
	}

	//返回显示的字符串吧, 60应该是字符上限
	override fun getPresentableText() = cutText(root.presentText(), 60)

	override fun getPresentation() = this
	override fun getValue() = root
	override fun getAlphaSortKey() = (root as? PsiNamedElement)?.name.orEmpty()
	override fun getChildren() = root
			.children
			.flatMap { (it as? ZigBlock)?.children?.toList() ?: listOf(it) }
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
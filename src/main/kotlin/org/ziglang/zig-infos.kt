package org.ziglang

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import org.ziglang.i18n.ZigBundle
import org.ziglang.icons.ZigIcons
import org.ziglang.psi.ZigGlobalVarDeclaration

object ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
    override fun getIcon() = ZigIcons.ZIG_FILE
    override fun getName() = ZIG_NAME
    override fun getDefaultExtension() = ZIG_EXTENSION
    override fun getDescription() = ZigBundle.message("zig.description")
}

class ZigFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage.INSTANCE) {
    override fun getFileType() = ZigFileType
    override fun processDeclarations(
        processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement
    ): Boolean = children.all {
        ((it as? ZigGlobalVarDeclaration)?.variableDeclaration ?: it).processDeclarations(
                processor,
                state,
                lastParent,
                place
            )
    }
}


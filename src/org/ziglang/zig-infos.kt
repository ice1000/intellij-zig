package org.ziglang

import com.intellij.CommonBundle
import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.codeInsight.template.impl.DefaultLiveTemplatesProvider
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.*
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.ResolveState
import com.intellij.psi.scope.PsiScopeProcessor
import icons.ZigIcons
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import org.ziglang.psi.ZigGlobalVarDeclaration
import org.ziglang.psi.ZigLocalVariableDeclaration
import java.util.*

object ZigFileType : LanguageFileType(ZigLanguage) {
	override fun getIcon() = ZigIcons.ZIG_FILE
	override fun getName() = ZIG_NAME
	override fun getDefaultExtension() = ZIG_EXTENSION
	override fun getDescription() = ZigBundle.message("zig.description")
}

class ZigFileTypeFactory : FileTypeFactory() {
	override fun createFileTypes(consumer: FileTypeConsumer) {
		consumer.consume(ZigFileType, ZIG_EXTENSION)
	}
}

class ZigFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, ZigLanguage) {
	override fun getFileType() = ZigFileType
	override fun processDeclarations(processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement): Boolean =
			children.all {
				((it as? ZigGlobalVarDeclaration)?.variableDeclaration ?: it)
						.processDeclarations(processor, state, lastParent, place)
			}
}

class ZigContext : TemplateContextType(ZIG_CONTEXT_ID, ZIG_NAME) {
	override fun isInContext(file: PsiFile, offset: Int) = file.fileType == ZigFileType
}

class ZigLiveTemplateProvider : DefaultLiveTemplatesProvider {
	private companion object DefaultHolder {
		private val DEFAULT = arrayOf("liveTemplates/Zig")
	}

	override fun getDefaultLiveTemplateFiles() = DEFAULT
	override fun getHiddenLiveTemplateFiles(): Array<String>? = null
}

object ZigBundle {
	@NonNls
	private const val BUNDLE = "org.ziglang.zig-bundle"
	private val bundle: ResourceBundle by lazy { ResourceBundle.getBundle(BUNDLE) }

	@JvmStatic
	fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
			CommonBundle.message(bundle, key, *params)
}


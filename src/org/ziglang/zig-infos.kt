package org.ziglang

import com.intellij.CommonBundle
import com.intellij.openapi.fileTypes.*
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.util.*
import javax.swing.Icon

object ZigFileType : LanguageFileType(ZigLanguage.INSTANCE) {
	override fun getIcon(): Icon? = null
	override fun getName() = ZIG_NAME
	override fun getDefaultExtension() = ZIG_EXTENSION
	override fun getDescription() = ZigBundle.message("zig.description")
}

class ZigFileTypeFactory : FileTypeFactory() {
	override fun createFileTypes(consumer: FileTypeConsumer) {
		consumer.consume(ZigFileType, ZIG_EXTENSION)
	}
}

object ZigBundle {
	@NonNls private const val BUNDLE = "org.ziglang.zig-bundle"
	private val bundle: ResourceBundle by lazy { ResourceBundle.getBundle(BUNDLE) }

	@JvmStatic
	fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
			CommonBundle.message(bundle, key, *params)
}


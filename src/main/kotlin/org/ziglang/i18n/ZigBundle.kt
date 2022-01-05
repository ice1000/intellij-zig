package org.ziglang.i18n

import com.intellij.AbstractBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.util.*

object ZigBundle {
    @NonNls
    private const val BUNDLE = "org.ziglang.zig-bundle"
    private val bundle: ResourceBundle by lazy { ResourceBundle.getBundle(BUNDLE) }

    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        AbstractBundle.message(bundle, key, *params)
}
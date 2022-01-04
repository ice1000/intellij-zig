package org.ziglang.error

import com.intellij.AbstractBundle
import org.jetbrains.annotations.NonNls
import org.jetbrains.annotations.PropertyKey
import java.util.*

/**
 * Messages and strings used by the error reporter
 */
object ErrorReportBundle {
    @NonNls
    private const val BUNDLE = "org.ziglang.error.report-bundle"
    private val bundle: ResourceBundle by lazy { ResourceBundle.getBundle(BUNDLE) }

    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any) =
        AbstractBundle.message(bundle, key, *params)
}
package org.ziglang

import com.intellij.lang.Language
import org.jetbrains.annotations.Contract

/**
 * @author ice1000
 */
object ZigLanguage : Language(ZIG_NAME, "text/$ZIG_EXTENSION") {
    @Contract(pure = true)
    override fun isCaseSensitive(): Boolean {
        return false
    }

}
package org.ziglang

import com.intellij.lang.Language
import org.jetbrains.annotations.Contract

/**
 * @author ice1000
 */
class ZigLanguage private constructor() : Language(ZIG_NAME, "text/$ZIG_EXTENSION") {
    @Contract(pure = true)
    override fun isCaseSensitive(): Boolean {
        return false
    }

    companion object {
        val INSTANCE = ZigLanguage()
    }
}
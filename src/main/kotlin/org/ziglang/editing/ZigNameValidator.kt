package org.ziglang.editing

import com.intellij.openapi.ui.InputValidatorEx
import org.ziglang.i18n.ZigBundle
import org.ziglang.util.orFalse

object ZigNameValidator : InputValidatorEx {
    override fun canClose(inputString: String?) = checkInput(inputString)
    override fun checkInput(inputString: String?) = inputString?.run {
        all {
            it.isLetterOrDigit() || it == '_'
        } && !firstOrNull()?.isDigit().orFalse()
    }.orFalse()

    override fun getErrorText(inputString: String?) =
        ZigBundle.message("zig.actions.new-file.invalid", inputString.orEmpty())
}
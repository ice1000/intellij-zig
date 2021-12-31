package org.ziglang.psi

import com.intellij.openapi.util.TextRange
import com.intellij.psi.AbstractElementManipulator
import org.ziglang.ZigTokenType

class ZigStringManipulator : AbstractElementManipulator<ZigString>() {
    override fun handleContentChange(psi: ZigString, range: TextRange, new: String): ZigString {
        val after = ZigTokenType.fromText(new, psi.project) as? ZigString ?: return psi
        psi.replace(after)
        return after
    }
}

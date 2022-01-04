package org.ziglang.editing

import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import org.ziglang.psi.ZigTypes

class ZigBraceMatcher : PairedBraceMatcher {
    private companion object PairHolder {
        private val PAIRS = arrayOf(
            BracePair(ZigTypes.LEFT_BRACE, ZigTypes.RIGHT_BRACE, false),
            BracePair(ZigTypes.LEFT_BRACKET, ZigTypes.RIGHT_BRACKET, false),
            BracePair(ZigTypes.LEFT_PAREN, ZigTypes.RIGHT_PAREN, false)
        )
    }

    override fun getCodeConstructStart(file: PsiFile?, openingBraceOffset: Int) = openingBraceOffset
    override fun getPairs() = PAIRS
    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true
}
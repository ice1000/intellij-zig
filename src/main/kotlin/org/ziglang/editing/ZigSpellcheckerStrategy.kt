package org.ziglang.editing

import com.intellij.psi.PsiComment
import com.intellij.psi.PsiElement
import com.intellij.spellchecker.tokenizer.SpellcheckingStrategy
import com.intellij.spellchecker.tokenizer.Tokenizer
import org.ziglang.psi.ZigString
import org.ziglang.psi.ZigSymbol

class ZigSpellcheckerStrategy : SpellcheckingStrategy() {
    override fun getTokenizer(element: PsiElement): Tokenizer<PsiElement> = when (element) {
        is PsiComment -> TEXT_TOKENIZER
        is ZigSymbol -> if (element.isDeclaration) TEXT_TOKENIZER else EMPTY_TOKENIZER
        is ZigString -> super.getTokenizer(element).takeIf { it != EMPTY_TOKENIZER } ?: TEXT_TOKENIZER
        else -> EMPTY_TOKENIZER
    }
}
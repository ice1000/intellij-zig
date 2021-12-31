package org.ziglang

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lexer.FlexAdapter
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiErrorElement
import com.intellij.psi.PsiFileFactory
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import org.ziglang.psi.ZigTypes

class ZigLexerAdapter : FlexAdapter(ZigLexer())

class ZigParserDefinition : ParserDefinition {
    override fun createParser(project: Project?) = ZigParser()
    override fun createLexer(project: Project?) = ZigLexerAdapter()
    override fun createFile(viewProvider: FileViewProvider) = ZigFile(viewProvider)
    override fun createElement(node: ASTNode?) = ZigTypes.Factory.createElement(node)
    override fun getStringLiteralElements() = ZigTokenType.STRINGS
    override fun getCommentTokens() = ZigTokenType.COMMENTS
    override fun getFileNodeType() = FILE
    override fun getWhitespaceTokens() = TokenSet.WHITE_SPACE
    override fun spaceExistanceTypeBetweenTokens(left: ASTNode?, right: ASTNode?) =
        ParserDefinition.SpaceRequirements.MAY

    private companion object FileHolder {
        private val FILE = IFileElementType(ZigLanguage.INSTANCE)
    }
}

class ZigTokenType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE) {
    companion object TokenHolder {
        @JvmField
        val LINE_COMMENT = ZigTokenType("comment")
        @JvmField
        val COMMENTS = TokenSet.create(LINE_COMMENT)
        @JvmField
        val STRINGS = TokenSet.create(ZigTypes.STR)
        @JvmField
        val IDENTIFIERS = TokenSet.create(ZigTypes.SYM, ZigTypes.SYMBOL)

        fun fromText(string: String, project: Project) = PsiFileFactory
            .getInstance(project)
            .createFileFromText(ZigLanguage.INSTANCE, string)
            .firstChild
            .let { (it as? PsiErrorElement)?.firstChild ?: it }
    }
}

class ZigElementType(debugName: String) : IElementType(debugName, ZigLanguage.INSTANCE)

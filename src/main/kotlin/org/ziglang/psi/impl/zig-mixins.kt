package org.ziglang.psi.impl

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.*
import com.intellij.psi.scope.PsiScopeProcessor
import com.intellij.psi.util.PsiTreeUtil
import org.ziglang.ZigTokenType
import org.ziglang.orTrue
import org.ziglang.psi.*

abstract class TrivialDeclaration(node: ASTNode) : ASTWrapperPsiElement(node), PsiNameIdentifierOwner {
    override fun getNameIdentifier() = PsiTreeUtil.findChildOfType(this, ZigSymbol::class.java)
    override fun setName(name: String) = also { nameIdentifier?.replace(ZigTokenType.fromText(name, project)) }
    override fun getName() = nameIdentifier?.text

    override fun processDeclarations(
        processor: PsiScopeProcessor, substitutor: ResolveState, lastParent: PsiElement?, place: PsiElement
    ) =
        nameIdentifier?.processDeclarations(processor, substitutor, lastParent, place).orTrue()
}

interface IZigSymbol : PsiNameIdentifierOwner {
    val isFunctionName: Boolean
    val isParameter: Boolean
    val isVariableName: Boolean
    val isDeclaration: Boolean
}

abstract class ZigVariableDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigVariableDeclaration
abstract class ZigParamDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigParamDeclaration
abstract class ZigFnDeclarationMixin(node: ASTNode) : TrivialDeclaration(node), ZigFnDeclaration {
    override fun processDeclarations(
        processor: PsiScopeProcessor,
        substitutor: ResolveState,
        lastParent: PsiElement?,
        place: PsiElement
    ) =
        fnProto.parameterList.paramDeclarationList.all {
            it.processDeclarations(
                processor,
                substitutor,
                lastParent,
                place
            )
        }
                && super.processDeclarations(processor, substitutor, lastParent, place)
}

abstract class ZigSymbolMixin(node: ASTNode) : ZigExprImpl(node), ZigSymbol {
    final override val isFunctionName: Boolean
        get() = parent is ZigFnProto && prevSiblingTypeIgnoring(
            ZigTypes.FN_KEYWORD,
            TokenType.WHITE_SPACE,
            ZigTokenType.LINE_COMMENT
        ) != null

    final override val isParameter: Boolean
        get() = parent is ZigParamDeclaration
    final override val isVariableName: Boolean
        get() = (parent is ZigVariableDeclaration) && (
                    prevSiblingTypeIgnoring(ZigTypes.CONST_KEYWORD, TokenType.WHITE_SPACE, ZigTokenType.LINE_COMMENT )?: 
                    prevSiblingTypeIgnoring(ZigTypes.VAR_KEYWORD, TokenType.WHITE_SPACE, ZigTokenType.LINE_COMMENT)
                ) != null

    override val isDeclaration: Boolean
        get() = isFunctionName ||
                isParameter ||
                isVariableName

    override fun getNameIdentifier() = this
    private var referenceImpl: ZigSymbolRef? = null

    /** For [ZigSymbolMixin], we cannot have a reference if it's a declaration. */
    override fun getReference() = referenceImpl ?: ZigSymbolRef(this).also { referenceImpl = it }

    override fun processDeclarations(
        processor: PsiScopeProcessor,
        state: ResolveState,
        lastParent: PsiElement?,
        place: PsiElement
    ) =
        processor.execute(this, state)

    override fun setName(name: String) = replace(ZigTokenType.fromText(name, project))
    override fun getName() = text
    override fun subtreeChanged() {
//		type = null  TODO ZigExpr implements IZigExpr
        referenceImpl = null
        super.subtreeChanged()
    }
}

abstract class ZigStringMixin(node: ASTNode) : ZigExprImpl(node), ZigString {
    override fun isValidHost() = true
    override fun updateText(text: String) = ElementManipulators.handleContentChange(this, text)
    override fun createLiteralTextEscaper() = LiteralTextEscaper.createSimple(this)
}

abstract class ZigBlockMixin(node: ASTNode) : ASTWrapperPsiElement(node), ZigBlock {
    override fun processDeclarations(
        processor: PsiScopeProcessor, state: ResolveState, lastParent: PsiElement?, place: PsiElement
    ) =
        statementList.all {
            it.firstChild
                .let { (it as? ZigLocalVariableDeclaration)?.variableDeclaration ?: it }
                .processDeclarations(processor, state, lastParent, place)
        }
}

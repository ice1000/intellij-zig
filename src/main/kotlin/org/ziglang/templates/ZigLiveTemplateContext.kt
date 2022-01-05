package org.ziglang.templates

import com.intellij.codeInsight.template.TemplateContextType
import com.intellij.psi.PsiFile
import org.ziglang.ZIG_CONTEXT_ID
import org.ziglang.ZIG_NAME
import org.ziglang.ZigFileType

class ZigLiveTemplateContext : TemplateContextType(ZIG_CONTEXT_ID, ZIG_NAME) {
    override fun isInContext(file: PsiFile, offset: Int) = (file.fileType == ZigFileType)
}
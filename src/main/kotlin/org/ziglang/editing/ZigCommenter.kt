package org.ziglang.editing

import com.intellij.lang.Commenter
import org.ziglang.ZIG_COMMENT_START

class ZigCommenter : Commenter {
    override fun getCommentedBlockCommentPrefix(): String? = blockCommentPrefix
    override fun getCommentedBlockCommentSuffix(): String? = blockCommentSuffix
    override fun getBlockCommentPrefix(): String? = null
    override fun getBlockCommentSuffix(): String? = null
    override fun getLineCommentPrefix() = ZIG_COMMENT_START
}
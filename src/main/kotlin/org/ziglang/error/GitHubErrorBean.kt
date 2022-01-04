package org.ziglang.error

import com.intellij.openapi.diagnostic.Attachment
import java.io.PrintWriter
import java.io.StringWriter
import java.util.*

/**
 * Extends the standard class to provide the hash of the thrown exception stack trace.
 *
 * @author patrick (17.06.17).
 */
class GitHubErrorBean(
    throwable: Throwable, val lastAction: String, val description: String, val message: String
) {
    val exceptionHash: String
    val stackTrace: String

    init {
        val trace = throwable.stackTrace
        exceptionHash = Arrays.hashCode(trace).toString()
        val sw = StringWriter()
        throwable.printStackTrace(PrintWriter(sw))
        stackTrace = sw.toString()
    }

    var pluginName = ""
    var pluginVersion = ""
    var attachments: List<Attachment> = emptyList()
}
/*
 * Copyright (c) 2017 Patrick Scheibe
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */

package org.ziglang.error

import com.intellij.openapi.diagnostic.Logger
import com.intellij.openapi.diagnostic.SubmittedReportInfo
import com.intellij.openapi.diagnostic.SubmittedReportInfo.SubmissionStatus
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task
import com.intellij.openapi.project.Project
import com.intellij.util.Consumer
import org.eclipse.egit.github.core.Issue
import org.eclipse.egit.github.core.Label
import org.eclipse.egit.github.core.RepositoryId
import org.eclipse.egit.github.core.client.GitHubClient
import org.eclipse.egit.github.core.service.IssueService
import java.io.IOException
import java.net.URL

private object AnonymousFeedback {
    private const val tokenFile = "org/ziglang/error/token.bin"
    private const val gitRepoUser = "babanin"
    private const val gitRepo = "intellij-zig"
    private const val issueLabel = "pending"

    /**
     * Makes a connection to GitHub. Checks if there is an issue that is a duplicate and based on this, creates either a
     * new issue or comments on the duplicate (if the user provided additional information).
     *
     * @param environmentDetails Information collected by [getKeyValuePairs]
     * @return The report info that is then used in [GitHubErrorReporter] to show the user a balloon with the link
     * of the created issue.
     */
    fun sendFeedback(environmentDetails: MutableMap<String, String>): SubmittedReportInfo {
        val logger = Logger.getInstance(javaClass.name)
        try {
            val resource: URL? = javaClass.classLoader.getResource(tokenFile)
            if (resource == null) {
                logger.info("Could not find token file")
                throw IOException("Could not decrypt access token")
            }
            val gitAccessToken = decrypt(resource)
            val client = GitHubClient()
            client.setOAuth2Token(gitAccessToken)
            val repoID = RepositoryId(gitRepoUser, gitRepo)
            val issueService = IssueService(client)
            var newGibHubIssue = createNewGibHubIssue(environmentDetails)
            val duplicate = findFirstDuplicate(newGibHubIssue.title, issueService, repoID)
            var isNewIssue = true
            if (duplicate != null) {
                issueService.createComment(repoID, duplicate.number, generateGitHubIssueBody(environmentDetails, false))
                newGibHubIssue = duplicate
                isNewIssue = false
            } else newGibHubIssue = issueService.createIssue(repoID, newGibHubIssue)
            return SubmittedReportInfo(
                newGibHubIssue.htmlUrl, ErrorReportBundle.message(
                    if (isNewIssue) "git.issue.text" else "git.issue.duplicate.text",
                    newGibHubIssue.htmlUrl,
                    newGibHubIssue.number.toLong()
                ), if (isNewIssue) SubmissionStatus.NEW_ISSUE else SubmissionStatus.DUPLICATE
            )
        } catch (e: Exception) {
            return SubmittedReportInfo(
                null, ErrorReportBundle.message("report.error.connection.failure"), SubmissionStatus.FAILED
            )
        }
    }

    private fun findFirstDuplicate(uniqueTitle: String, service: IssueService, repo: RepositoryId): Issue? {
        val searchParameters = HashMap<String, String>(2)
        searchParameters[IssueService.FILTER_STATE] = IssueService.STATE_OPEN
        return service.pageIssues(repo, searchParameters).flatten().firstOrNull { it.title == uniqueTitle }
    }

    private fun createNewGibHubIssue(details: MutableMap<String, String>) = Issue().apply {
        val errorMessage = details.remove("error.message")?.takeIf(String::isNotBlank) ?: "Unspecified error"
        title = ErrorReportBundle.message("git.issue.title", details.remove("error.hash").orEmpty(), errorMessage)
        details["title"] = title
        body = generateGitHubIssueBody(details, true)
        labels = listOf(Label().apply { name = issueLabel })
    }

    private fun generateGitHubIssueBody(details: MutableMap<String, String>, includeStacktrace: Boolean) = buildString {
        val errorDescription = details.remove("error.description").orEmpty()
        val stackTrace = details.remove("error.stacktrace")?.takeIf(String::isNotBlank) ?: "invalid stacktrace"
        if (errorDescription.isNotEmpty()) append(errorDescription).appendLine("\n\n----------------------\n")
        for ((key, value) in details) append("- ").append(key).append(": ").appendLine(value)
        if (includeStacktrace) appendLine("\n```").appendLine(stackTrace).appendLine("```")
    }
}

internal class AnonymousFeedbackTask(
    project: Project?,
    title: String,
    canBeCancelled: Boolean,
    private val params: MutableMap<String, String>,
    private val callback: Consumer<SubmittedReportInfo>
) : Task.Backgroundable(project, title, canBeCancelled) {
    override fun run(indicator: ProgressIndicator) {
        indicator.isIndeterminate = true
        callback.consume(AnonymousFeedback.sendFeedback(params))
    }
}


package org.ziglang.error

import com.intellij.diagnostic.AbstractMessage
import com.intellij.diagnostic.DiagnosticBundle
import com.intellij.ide.DataManager
import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.ide.plugins.PluginUtil
import com.intellij.idea.IdeaLogger
import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationListener
import com.intellij.notification.NotificationType
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.ApplicationNamesInfo
import com.intellij.openapi.application.ex.ApplicationInfoEx
import com.intellij.openapi.diagnostic.ErrorReportSubmitter
import com.intellij.openapi.diagnostic.IdeaLoggingEvent
import com.intellij.openapi.diagnostic.SubmittedReportInfo
import com.intellij.openapi.progress.EmptyProgressIndicator
import com.intellij.openapi.progress.ProgressManager
import com.intellij.openapi.project.Project
import com.intellij.util.Consumer
import java.awt.Component

class GitHubErrorReporter : ErrorReportSubmitter() {
    override fun getReportActionText() = ErrorReportBundle.message("report.error.to.plugin.vendor")

    override fun submit(
        events: Array<out IdeaLoggingEvent>,
        additionalInfo: String?,
        parentComponent: Component,
        consumer: Consumer<in SubmittedReportInfo>
    ): Boolean {
        val event = events.firstOrNull { it.throwable != null } ?: return false
        return doSubmit(event, parentComponent, consumer, additionalInfo)
    }

    private fun doSubmit(
        event: IdeaLoggingEvent, parent: Component, callback: Consumer<in SubmittedReportInfo>, description: String?
    ): Boolean {
        val dataContext = DataManager.getInstance().getDataContext(parent)
        val bean = GitHubErrorBean(
            event.throwable,
            IdeaLogger.ourLastActionId.orEmpty(),
            description ?: "<No description>",
            event.message ?: event.throwable.message.toString()
        )
        PluginUtil.getInstance().findPluginId(event.throwable)?.let { pluginId ->
            PluginManagerCore.getPlugin(pluginId)?.let { ideaPluginDescriptor ->
                if (!ideaPluginDescriptor.isBundled) {
                    bean.pluginName = ideaPluginDescriptor.name
                    bean.pluginVersion = ideaPluginDescriptor.version
                }
            }
        }

        (event.data as? AbstractMessage)?.let { bean.attachments = it.includedAttachments }
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val reportValues = getKeyValuePairs(
            project, bean, ApplicationInfoEx.getInstanceEx(), ApplicationNamesInfo.getInstance()
        )
        val notifyingCallback = CallbackWithNotification(callback, project)
        val task = AnonymousFeedbackTask(
            project, ErrorReportBundle.message(
                "report.error.progress.dialog.text"
            ), true, reportValues, notifyingCallback
        )
        if (project == null) task.run(EmptyProgressIndicator()) else ProgressManager.getInstance().run(task)
        return true
    }

    internal class CallbackWithNotification(
        private val consumer: Consumer<in SubmittedReportInfo>, private val project: Project?
    ) : Consumer<SubmittedReportInfo> {
        override fun consume(reportInfo: SubmittedReportInfo) {
            consumer.consume(reportInfo)

            val errorReportTitle = DiagnosticBundle.message("error.report.title")
            val notificationGroup = NotificationGroupManager.getInstance().getNotificationGroup("Error Report")

            if (reportInfo.status == SubmittedReportInfo.SubmissionStatus.FAILED) {
                notificationGroup.createNotification(errorReportTitle, reportInfo.linkText, NotificationType.ERROR)
                    .apply {
                        isImportant = true
                    }.also {
                        it.notify(project)
                    }
            } else {
                notificationGroup.createNotification(
                    errorReportTitle,
                    reportInfo.linkText,
                    NotificationType.INFORMATION
                ).apply {
                    isImportant = true
                }.also {
                    it.setListener(NotificationListener.URL_OPENING_LISTENER)
                    it.notify(project)
                }
            }
        }
    }
}
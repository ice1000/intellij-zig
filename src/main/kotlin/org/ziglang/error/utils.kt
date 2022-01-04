package org.ziglang.error

import com.intellij.ide.plugins.PluginManagerCore
import com.intellij.openapi.application.ApplicationNamesInfo
import com.intellij.openapi.application.ex.ApplicationInfoEx
import com.intellij.openapi.extensions.PluginId
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo
import org.apache.commons.codec.binary.Base64
import org.ziglang.ZIG_PLUGIN_ID
import org.ziglang.project.zigSettings
import java.io.ObjectInputStream
import java.net.URL
import java.nio.file.Files
import java.nio.file.Paths
import javax.crypto.Cipher
import javax.crypto.spec.IvParameterSpec
import javax.crypto.spec.SecretKeySpec

private const val initVector = "RandomInitVector"

private const val key = "GitHubErrorToken"

internal fun decrypt(file: URL): String {
    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(
        Cipher.DECRYPT_MODE,
        SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES"),
        IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
    )
    return String(cipher.doFinal(Base64.decodeBase64(file.openStream().use {
        ObjectInputStream(it).use(ObjectInputStream::readObject) as String
    })))
}

fun main(args: Array<String>) {
    if (args.size != 2) return
    Files.write(Paths.get(args[1]), encrypt(args[0]).toByteArray())
}

internal fun encrypt(value: String): String {
    val cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING")
    cipher.init(
        Cipher.ENCRYPT_MODE,
        SecretKeySpec(key.toByteArray(charset("UTF-8")), "AES"),
        IvParameterSpec(initVector.toByteArray(charset("UTF-8")))
    )
    return Base64.encodeBase64String(cipher.doFinal(value.toByteArray()))
}

/**
 * Collects information about the running IDEA and the error
 */
internal fun getKeyValuePairs(
    project: Project?, error: GitHubErrorBean, appInfo: ApplicationInfoEx, namesInfo: ApplicationNamesInfo
): MutableMap<String, String> {
    PluginManagerCore.getPlugin(PluginId.findId(ZIG_PLUGIN_ID))?.run {
        if (error.pluginName.isBlank()) error.pluginName = name
        if (error.pluginVersion.isBlank()) error.pluginVersion = version
    }

    val params = mutableMapOf("error.description" to error.description,
        "Zig Version" to (project?.run { zigSettings.settings.version } ?: "Not properly configured"),
        "Plugin Name" to error.pluginName,
        "Plugin Version" to error.pluginVersion,
        "OS Name" to SystemInfo.OS_NAME,
        "Java Version" to SystemInfo.JAVA_VERSION,
        "App Name" to namesInfo.productName,
        "App Full Name" to namesInfo.fullProductName,
        "App Version name" to appInfo.versionName,
        "Is EAP" to java.lang.Boolean.toString(appInfo.isEAP),
        "App Build" to appInfo.build.asString(),
        "App Version" to appInfo.fullVersion,
        "Last Action" to error.lastAction,
        "error.message" to error.message,
        "error.stacktrace" to error.stackTrace,
        "error.hash" to error.exceptionHash)
    for (attachment in error.attachments) params["Attachment ${attachment.name}"] = attachment.encodedBytes
    return params
}
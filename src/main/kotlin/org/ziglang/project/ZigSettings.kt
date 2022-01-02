package org.ziglang.project

import java.nio.file.Paths

class ZigSettings constructor(
    var exePath: String = zigGlobalSettings.knownZigExes.firstOrNull() ?: zigPath,
    var version: String = "",
    var installPath: String = ""
) {
    fun initWithExe() {
        version = versionOf(exePath)
        installPath = Paths.get(exePath).parent.toAbsolutePath().toString()
    }
}
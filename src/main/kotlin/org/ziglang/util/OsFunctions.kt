package org.ziglang.util

import org.ziglang.util.OS.*
import java.nio.file.Files
import java.nio.file.Paths

enum class OS {
    WINDOWS, LINUX, MAC, SOLARIS;

    companion object {
        val current: OS? by lazy {
            val os = System.getProperty("os.name").lowercase()

            when {
                os.contains("win") -> WINDOWS
                os.contains("nix") || os.contains("nux") || os.contains("aix") -> LINUX
                os.contains("mac") -> MAC
                os.contains("sunos") -> SOLARIS
                else -> null
            }
        }
    }
}

object OsSpecific : OsFunctions {
    private val osImplementation: OsFunctions by lazy {
        when (OS.current) {
            WINDOWS -> OsWindows
            LINUX -> OsLinux
            MAC -> OsMac
            SOLARIS -> OsSolaris
            null -> TODO()
        }
    }

    override fun isValidSdkPath(sdkHome: String): Boolean =
        osImplementation.isValidSdkPath(sdkHome)

}

sealed interface OsFunctions {
    fun isValidSdkPath(sdkHome: String): Boolean
}

object OsWindows : OsFunctions {
    override fun isValidSdkPath(sdkHome: String): Boolean =
        Files.exists(Paths.get(sdkHome, "zig.exe"))
}

object OsLinux : OsFunctions {
    override fun isValidSdkPath(sdkHome: String): Boolean =
        Files.exists(Paths.get(sdkHome, "bin", "zig"))

}

object OsMac : OsFunctions {
    override fun isValidSdkPath(sdkHome: String): Boolean =
        Files.exists(Paths.get(sdkHome, "bin", "zig"))

}

object OsSolaris : OsFunctions {
    override fun isValidSdkPath(sdkHome: String): Boolean =
        Files.exists(Paths.get(sdkHome, "bin", "zig"))
}
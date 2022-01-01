package org.ziglang.icons

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/**
 * Icon holder class
 *
 * @author ice1000, HoshinoTended
 */
interface ZigIcons {
    companion object {
        private fun icon(name: String): Icon = IconLoader.getIcon("/icons/${name}", ZigIcons::class.java)

        val ZIG_BIG_ICON = icon("zig_icon.png")
        val ZIG_ADD_SDK_ICON = icon("zig_sdk.png")
        val ZIG_WEBSITE_ICON = icon("zig.png")

        // @NotNull Icon ZIG_RESOURCE_ICON = icon("zig_resource.png");
        val ZIG_FILE = icon("zig_file.png")
        val ZIG_VAR = icon("zig_variable.png")
        val ZIG_FUN = icon("zig_function.png")
    }
}
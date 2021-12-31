package icons

import com.intellij.openapi.util.IconLoader

/**
 * Icon holder class
 *
 * @author ice1000, HoshinoTended
 */
interface ZigIcons {
    companion object {
        val ZIG_BIG_ICON = IconLoader.getIcon("/icons/zig_icon.png")
        val ZIG_ADD_SDK_ICON = IconLoader.getIcon("/icons/zig_sdk.png")
        val ZIG_WEBSITE_ICON = IconLoader.getIcon("/icons/zig.png")

        // @NotNull Icon ZIG_RESOURCE_ICON = IconLoader.getIcon("/icons/zig_resource.png");
        val ZIG_FILE = IconLoader.getIcon("/icons/zig_file.png")
        val ZIG_VAR = IconLoader.getIcon("/icons/zig_variable.png")
        val ZIG_FUN = IconLoader.getIcon("/icons/zig_function.png")
    }
}
package icons;

import com.intellij.openapi.util.IconLoader;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

/**
 * Icon holder class
 *
 * @author ice1000, HoshinoTended
 */
public interface ZigIcons {
	@NotNull Icon ZIG_BIG_ICON = IconLoader.getIcon("/icons/zig_icon.png", ZigIcons.class);
	@NotNull Icon ZIG_ADD_SDK_ICON = IconLoader.getIcon("/icons/zig_sdk.png", ZigIcons.class);
	@NotNull Icon ZIG_WEBSITE_ICON = IconLoader.getIcon("/icons/zig.png", ZigIcons.class);
	// @NotNull Icon ZIG_RESOURCE_ICON = IconLoader.getIcon("/icons/zig_resource.png", ZigIcons.class);
	@NotNull Icon ZIG_FILE = IconLoader.getIcon("/icons/zig_file.png", ZigIcons.class);
	@NotNull Icon ZIG_VAR = IconLoader.getIcon("/icons/zig_variable.png", ZigIcons.class);
	@NotNull Icon ZIG_FUN = IconLoader.getIcon("/icons/zig_function.png", ZigIcons.class);
}

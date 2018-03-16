package org.ziglang;

import com.intellij.lang.Language;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

import static org.ziglang.Zig_constantsKt.ZIG_EXTENSION;
import static org.ziglang.Zig_constantsKt.ZIG_NAME;

/**
 * @author ice1000
 */
public class ZigLanguage extends Language {
	public static final @NotNull ZigLanguage INSTANCE = new ZigLanguage();

	private ZigLanguage() {
		super(ZIG_NAME, "text/" + ZIG_EXTENSION);
	}

	@Override @Contract(pure = true) public boolean isCaseSensitive() {
		return false;
	}
}

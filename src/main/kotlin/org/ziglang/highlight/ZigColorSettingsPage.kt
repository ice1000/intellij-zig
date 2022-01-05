package org.ziglang.highlight

import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import org.ziglang.i18n.ZigBundle
import org.ziglang.ZigFileType
import org.ziglang.icons.ZigIcons

class ZigColorSettingsPage : ColorSettingsPage {
    private companion object DescriptionHolder {
        private val DESCRIPTION = arrayOf(
            AttributesDescriptor(ZigBundle.message("zig.highlighter.settings.keyword"), ZigSyntaxHighlighter.KEYWORD),
            AttributesDescriptor(ZigBundle.message("zig.highlighter.settings.number"), ZigSyntaxHighlighter.NUMBER),
            AttributesDescriptor(ZigBundle.message("zig.highlighter.settings.string"), ZigSyntaxHighlighter.STRING),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.string-escape"),
                ZigSyntaxHighlighter.STRING_ESCAPE
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.string-escape-invalid"),
                ZigSyntaxHighlighter.STRING_ESCAPE_INVALID
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.comment"),
                ZigSyntaxHighlighter.LINE_COMMENT
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.operator.ordinary"),
                ZigSyntaxHighlighter.OPERATOR
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.operator.semicolon"),
                ZigSyntaxHighlighter.SEMICOLON
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.operator.parens"),
                ZigSyntaxHighlighter.PAREN
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.operator.brackets"),
                ZigSyntaxHighlighter.BRACKET
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.operator.braces"),
                ZigSyntaxHighlighter.BRACE
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.reference.ordinary"),
                ZigSyntaxHighlighter.SYMBOL
            ),
            AttributesDescriptor(
                ZigBundle.message("zig.highlighter.settings.reference.builtin"),
                ZigSyntaxHighlighter.BUILTIN_FUNCTION_CALL
            )
        )

        private val ADDITIONAL_DESCRIPTIONS = mapOf(
            "functionName" to ZigSyntaxHighlighter.FUNCTION_DECLARATION,
            "builtinFunction" to ZigSyntaxHighlighter.BUILTIN_FUNCTION_CALL,
            "escape" to ZigSyntaxHighlighter.STRING_ESCAPE,
            "escapeInvalid" to ZigSyntaxHighlighter.STRING_ESCAPE_INVALID
        )
    }

    override fun getHighlighter() = ZigSyntaxHighlighter

    override fun getAdditionalHighlightingTagToDescriptorMap() = ADDITIONAL_DESCRIPTIONS

    override fun getIcon() = ZigIcons.ZIG_BIG_ICON

    override fun getAttributeDescriptors() = DESCRIPTION

    override fun getColorDescriptors(): Array<ColorDescriptor> = ColorDescriptor.EMPTY_ARRAY

    override fun getDisplayName() = ZigFileType.name

    // @Language("Zig")
    override fun getDemoText() =
        """
		// Comment
		const std = @<builtinFunction>import</builtinFunction>("std");
		pub fn <functionName>main</functionName>() !void {
		    var aVar = []f64{1 + 2.0};
		    std.debug.warn("This is a<escape>\n</escape>new line<escapeInvalid>\g</escapeInvalid>");
		}
		""".trimIndent()
}
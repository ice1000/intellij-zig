package org.ziglang

import org.jetbrains.annotations.NonNls

@NonNls
const val ZIG_NAME = "Zig"
@NonNls
const val ZIG_EXTENSION = "zig"
@NonNls
const val ZIG_WEBSITE = "https://ziglang.org"

@NonNls
const val ZIG_CONTEXT_ID = "ZIG_CONTEXT_ID"
@NonNls
const val ZIG_PLUGIN_ID = "org.ziglang"
@NonNls
const val ZIG_RUN_CONFIG_ID = "ZIG_RUN_CONFIG_ID"

@NonNls
const val ZIG_COMMENT_START = "//"

@JvmField
val builtinFunctions = listOf(
    "addWithOverflow",
    "ArgType",
    "atomicRmw",
    "bitCast",
    "breakpoint",
    "alignCast",
    "alignOf",
    "Define",
    "Import",
    "Include",
    "Undef",
    "canImplicitCast",
    "clz",
    "cmpxchg",
    "compileError",
    "compileLog",
    "ctz",
    "divExact",
    "divFloor",
    "divTrunc",
    "embedFile",
    "export",
    "tagName",
    "TagType",
    "errorName",
    "errorReturnTrace",
    "fence",
    "fieldParentPtr",
    "frameAddress",
    "import",
    "inlineCall",
    "intToPtr",
    "IntType",
    "maxValue",
    "memberCount",
    "memberName",
    "memberType",
    "memcpy",
    "memset",
    "minValue",
    "mod",
    "mulWithOverflow",
    "noInlineCall",
    "offsetOf",
    "OpaqueType",
    "panic",
    "ptrCast",
    "ptrToInt",
    "rem",
    "returnAddress",
    "setAlignStack",
    "setCold",
    "setRuntimeSafety",
    "setEvalBranchQuota",
    "setFloatMode",
    "setGlobalLinkage",
    "setGlobalSection",
    "shlExact",
    "shlWithOverflow",
    "shrExact",
    "sizeOf",
    "subWithOverflow",
    "truncate",
    "typeId",
    "typeName",
    "typeOf"
)

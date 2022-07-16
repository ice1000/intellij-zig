Zig plugin for the IntelliJ Platform
====================================

<!-- Plugin description -->
Zig language support.
Functions provided:

* Syntax highlight (customizable)
* Sdk management
* Convert C files to Zig files via Ctrl+Alt+Shift+K
* Live templates
* Keywords based completions
* Code execution
* Console output linkenizer
* Run configuration management
* Commenter
* Find usages
* Reference resolving
* Renaming
* Brace matcher
* Spell checker
* Inspections
* Quick fixes
* Bread crumbs
* Code folding
* Structure view
* Run Zig tests

This is a work in progress, some features are implemented partially, there may be performance and stability problems.

<!-- Plugin description end -->

# TODO

## Zig 0.3+ syntax support: 

   * array literal syntax when inferring the size  `[_]i32{1, 2, 3}`
   * Anonymous List Literals  `.{1, 2}`
   * Type Coercion Syntax: `var a = @as(T, b);`
   * Sentinel-Terminated Pointers: `*const [N:0]u8`

# Screenshots

![](https://plugins.jetbrains.com/files/10560/screenshot_17959.png)
![](https://plugins.jetbrains.com/files/10560/screenshot_17965.png)

# References

* Zig Language: https://ziglang.org/
* Zig Docs: https://ziglang.org/documentation/0.9.1/
* Zig Grammar: https://ziglang.org/documentation/master/#Grammar
* Zig Learn: https://ziglearn.org/
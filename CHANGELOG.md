<!-- Keep a Changelog guide -> https://keepachangelog.com -->

# jetbrains-just-plugin Changelog

## [Unreleased]

## [0.2.0]

- LineMark icon for main function: run main method from mark icon
- LineMark icon for test functions: run Zig tests from test mark icon
- Adopt IntelliJ Platform Plugin Template structure
- Compatible with 2022.*
- Zig 0.4+ syntax support: anonymous List Literals  `.{1, 2}`

## [0.1.2]

- Fix SDK validation error
- Bumped minimum supported platform version to 173

## [0.1.1]

- IntelliJ 2019.1 capability
- Some Zig 0.3 syntax support
- Resolving and completion for function parameters
- Find usages
- Plugin icon

## [0.0.3]

- Color settings page
- Code folding
- Bread crumbs
- Console linkenizer
- Fix bug of build action (it was invisible on non-IDEA IDEs)
- Tool action: display Zig Zen
- Tool action: convert C file to Zig file
- Parser bug fixes
- Lexer refactoring (built-in function calls are no longer "symbol"s now)

## [0.0.2]

- Configure output path in run config
- Configure various compiler flags in run config
- Spell checker
- Completions based on keywords
- Completions based on built-in functions
- Highlight built-in functions
- One quick-fix
#!/usr/bin/env just --justfile

# record some of the colour formatting
bold := '\033[1m'
normal := '\033[0m'
red := '\033[0;31m'
blue := '\033[0;34m'

# Default with basic intro text
welcome:
  @echo "{{blue}}{{bold}}Welcome{{normal}}"
  @echo "this is normal"

# Generate Parser and Lexer
generate:
   rm -rf src/main/gen/org/
   ./gradlew generateLexer generateParser

# Build just plugin
build-plugin: clean generate
  ./gradlew patchPluginXml buildPlugin

# clean
clean:
  rm -rf build/distributions
  rm -rf build/resources
  rm -rf build/classes

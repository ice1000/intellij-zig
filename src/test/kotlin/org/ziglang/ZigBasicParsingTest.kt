package org.ziglang

import com.intellij.testFramework.LightPlatformTestCase

class ZigBasicParsingTest : LightPlatformTestCase() {
	fun testParse() {
		ZigTokenType.fromText("abc", project).javaClass
				.let(::println)
	}
}
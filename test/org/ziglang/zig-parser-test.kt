package org.ziglang

import com.intellij.testFramework.ParsingTestCase

class ZigParserTests : ParsingTestCase("", ZIG_EXTENSION, ZigParserDefinition()) {
	override fun getTestDataPath() = "testData"
	override fun skipSpaces() = true

	fun testComments() {
		println(name)
		doTest(true)
	}

	fun testParsing0() {
		println(name)
		doTest(true)
	}

	fun testStrings() {
		println(name)
		doTest(true)
	}

	fun testNumbers() {
		println(name)
		doTest(true)
	}

	fun testFunctionCall() {
		println(name)
		doTest(true)
	}

	fun testfloat_mode() {
		println(name)
		doTest(true)
	}
}

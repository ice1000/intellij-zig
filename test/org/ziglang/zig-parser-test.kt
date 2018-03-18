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
}

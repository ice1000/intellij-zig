package org.ziglang

import com.intellij.testFramework.ParsingTestCase
import kotlin.test.Test

class ZigParserTests : ParsingTestCase("", ZIG_EXTENSION, ZigParserDefinition()) {
	override fun getTestDataPath() = "src/test/resources/parsing"
	override fun skipSpaces() = true

	@Test
	fun testComments() {
		println(name)
		doTest(true)
	}

	fun testParsing0() {
		println(name)
		doTest(true)
	}

	fun testComptimeCodes() {
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

	fun testParameters() {
		println(name)
		doTest(true)
	}

	fun testfloat_mode() {
		println(name)
		doTest(true)
	}

	fun testfoo() {
		println(name)
		doTest(true)
	}

	fun testarrays() {
		println(name)
		doTest(true)
	}

	fun testvalues() {
		println(name)
		doTest(true)
	}

	fun testIf() {
		println(name)
		doTest(true)
	}

	fun testHelloWorld() {
		println(name)
		doTest(true)
	}
}


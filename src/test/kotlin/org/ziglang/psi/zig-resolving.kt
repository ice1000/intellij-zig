package org.ziglang.psi

import com.intellij.testFramework.fixtures.LightCodeInsightFixtureTestCase


class ZigResolvingTest : LightCodeInsightFixtureTestCase() {
	override fun getTestDataPath() = "testData/resolving"

	fun testSimple() {
		myFixture.configureByFiles("values.zig")
		val reference = myFixture
				.file
				.findElementAt(myFixture.caretOffset)
				?.parent
				?.reference
				?: kotlin.test.fail()
		val resolve = reference.resolve()
		assertNotNull(resolve)
	}

	fun testGlobal() {
		myFixture.configureByFiles("values2.zig")
		val reference = myFixture
				.file
				.findElementAt(myFixture.caretOffset)
				?.parent
				?.reference
				?: kotlin.test.fail()
		val resolve = reference.resolve()
		assertNotNull(resolve)
	}
}
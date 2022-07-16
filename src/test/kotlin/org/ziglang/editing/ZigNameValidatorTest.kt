package org.ziglang.editing

import org.junit.Assert.assertTrue
import org.junit.Test
import kotlin.test.assertFalse

class ZigNameValidatorTest {
	@Test
	fun checkInput() {
		assertTrue(ZigNameValidator.checkInput("abc"))
		assertTrue(ZigNameValidator.checkInput("abc1a"))
		assertTrue(ZigNameValidator.checkInput("abc_"))
		assertTrue(ZigNameValidator.checkInput("_abc_"))
		assertFalse(ZigNameValidator.checkInput("!abc"))
		assertFalse(ZigNameValidator.checkInput("1ab1c"))
		assertFalse(ZigNameValidator.checkInput(" 1abc"))
	}
}
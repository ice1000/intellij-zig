package org.ziglang

import org.junit.Test

import org.junit.Assert.*

class UtilsKtTest {
	@Test(timeout = 1000L)
	fun executeCommand() {
		if (!System.getenv("CI").isNullOrBlank()) return
		executeCommand("zig version", timeLimit = 5000L)
				.let(::println)
	}
}
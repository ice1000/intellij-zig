package org.ziglang

import org.junit.Test
import kotlin.test.assertEquals

class KotlinFeature {
	@Test
	fun testTrimIndent() {
		assertEquals("""
function main()
	println("Shit!")
end
""".trimEnd('\n'),
				"""
			function main()
				println("Shit!")
			end
""".trimIndent().trimEnd('\n'))
	}
}

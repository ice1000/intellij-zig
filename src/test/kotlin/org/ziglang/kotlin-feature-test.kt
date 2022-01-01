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
""".trim('\n'),
				"""
			function main()
				println("Shit!")
			end
""".trimIndent().trim('\n'))
	}
}

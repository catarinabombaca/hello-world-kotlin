package hello.world.kotlin

import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Status
import kotlin.test.Test
import kotlin.test.assertEquals

class AppTest {
    @Test fun appHasAGreeting() {
        val resp = greetingHandler(Request(Method.GET, "/greeting"))

        assertEquals(Status.OK, resp.status)
        assertEquals("Hello, world!", resp.bodyString())
    }
}

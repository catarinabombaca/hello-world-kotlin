package hello.world.kotlin

import org.http4k.core.HttpHandler
import org.http4k.core.Method
import org.http4k.core.Request
import org.http4k.core.Response
import org.http4k.core.Status.Companion.OK
import org.http4k.routing.bind
import org.http4k.routing.routes
import org.http4k.server.Undertow
import org.http4k.server.asServer

fun greetingHandler(request: Request): Response = Response(OK).body("Hello, world!")

val app: HttpHandler = routes(
    "greeting" bind Method.GET to ::greetingHandler
)

fun main() {
    //TODO: how to configure to say a different port depending on local or docker?
    val server= app.asServer(Undertow(9000)).start()
    println("Server started on port ${server.port()} 🚀")
}

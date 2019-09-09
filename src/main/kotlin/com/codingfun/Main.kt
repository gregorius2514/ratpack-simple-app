package com.codingfun

import ratpack.server.RatpackServer

class Main {

    fun runServer() {
        RatpackServer.start { server ->
            server.handlers { chain ->
                // handling home page
                chain.get { ctx -> ctx.render("Welcome to Ratpack") }
                // handling path page
                chain.get("foo") { ctx -> ctx.render("Welcome to bar") }
                chain.get("custom") { ctx ->
                    ctx.byMethod { c ->
                        c.get { ctx.render("get method") }
                        c.post { ctx.render("post method") }
                    }
                }
                chain.get("hello/:id") { ctx ->
                    val id = ctx.pathTokens.get("id") ?: "-1"
                    ctx.response.send("Hello: $id")
                }
                chain.get("find") { ctx ->
                    val queryName = ctx.request.queryParams.get("name")
                    ctx.response.send("Found: $queryName")
                    ctx.response.send(
                        """
                            <html>
                            <body>
                            <h1>$queryName</h1>
                            </body>
                            </html>
                        """.trimIndent()
                    )
                }

            }
        }
    }
}

fun main() {
    Main().runServer()
}
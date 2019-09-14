package com.codingfun

import com.google.inject.Inject
import ratpack.guice.Guice
import ratpack.http.Request
import ratpack.server.RatpackServer

class Main {

    fun runServer() {
        RatpackServer.start { server ->
            server.registry(
                Guice.registry {
                    b -> b.bind(ReqHandler::class.java)
                }
            )
            .handlers { chain ->
                chain.get { ctx -> 
               
                    val handler = ctx.get(ReqHandler::class.java)
                    handler.helloWorld()
                    ctx.render("Welcome to Ratpack") 
                
                }
            }
        }
    }
}

fun main() {
    Main().runServer()
}

class ReqHandler @Inject constructor(private val request: Request) {

    fun helloWorld() {
        val hCookie = request.headers.get("Cookie").split("=").toTypedArray()
        
        val cookieName = hCookie[0]
        val cookieValue = hCookie[1]
        
        println("Hello world cookie: , $cookieValue")
    }
}
package com.example

import ratpack.guice.BindingsSpec
import ratpack.guice.Guice
import ratpack.handling.Chain
import ratpack.server.RatpackServer

private const val APPLICATION_INPUT_PORT = 5050

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        RatpackServer.start { server ->
            server.serverConfig {
                it.port(APPLICATION_INPUT_PORT)
            }
                    .registry {
                        Guice.registry { binding ->
                            buildApplicationDependencies(binding)
                        }.apply(it)
                    }
                    .handlers { chain ->
                        defineApplicationHttpHandlers(chain)
                    }
        }
    }

    private fun defineApplicationHttpHandlers(chain: Chain) {
        chain.prefix("hello"){
            it.get(HelloWorldHandler::class.java)
        }
    }

    private fun buildApplicationDependencies(binding: BindingsSpec) {
        binding
                .bind(HelloWorldHandler::class.java)
    }
}


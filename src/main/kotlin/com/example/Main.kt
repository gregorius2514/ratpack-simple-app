package com.example

import ratpack.guice.Guice
import ratpack.server.RatpackServer

object Main {

    @JvmStatic
    fun main(args: Array<String>) {
        RatpackServer.start { server ->
            server.serverConfig {
                it.port(5050)
            }
                .registry {
                    Guice.registry { binding ->
                        binding
                            .bind(HelloWorldHandler::class.java)
                            .binder {
                                it.bind(ManualExecutor::class.java)
                                    .toProvider(ManualExecutorProvider::class.java)
                                    .asEagerSingleton()
                            }
                            .bind(HttpAsync::class.java)
                    }.apply(it)
                }
                .handlers { chain ->
                    chain.prefix("hi") {
                        it.get(HelloWorldHandler::class.java)
                    }
                    chain.prefix("bye") {
                        it.all {
                            it.byMethod {
                                it.get { a ->
                                    a.render("bye")
                                }
                                it.post { a ->
                                    a.render("bye post")
                                }
                            }
                        }
                    }
                }
        }
    }
}
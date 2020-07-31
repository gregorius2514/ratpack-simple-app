package com.example

import com.google.inject.Inject
import com.google.inject.Provider
import ratpack.exec.Blocking
import ratpack.guice.Guice
import ratpack.handling.Context
import ratpack.handling.Handler
import ratpack.http.client.HttpClient
import ratpack.server.RatpackServer
import java.net.URI

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
                    }.apply(it)
                }
                .handlers { chain ->
                    chain.prefix("hi") {
                        it.get(HelloWorldHandler::class.java)
                    }
                }
        }
    }
}

class HelloWorldHandler : Handler {
    override fun handle(ctx: Context) {
        ctx.render("hello world")
    }
}

class ManualExecutor

class ManualExecutorProvider @Inject constructor(
    val httpClient: HttpClient
) : Provider<ManualExecutor> {
    override fun get(): ManualExecutor {
        println("Manual executor provider")


        return ManualExecutor()
    }
}
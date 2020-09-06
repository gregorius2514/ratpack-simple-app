package com.example

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.handling.Handler
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class HelloWorldHandler : Handler {
    override fun handle(context: Context) {

        Single.just("Hello world")
                .delay(5, TimeUnit.SECONDS, Schedulers.trampoline())
                .subscribe { text ->
                    context.render(text)
                }
    }
}
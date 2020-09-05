package com.example

import ratpack.exec.Promise
import ratpack.handling.Context
import ratpack.handling.Handler
import kotlin.random.Random

class HelloWorldHandler : Handler {
    override fun handle(context: Context) {

        Promise.value("ala ma kota")
                .map { 
                    val delay = Random(System.currentTimeMillis()).nextLong(0, 10000)
                    println("Generated delay: [$delay]")
                    Thread.sleep(delay)
                    it
                }
                .then { context.render(it) }
    }
}
package com.example

import io.reactivex.Single
import io.reactivex.schedulers.Schedulers
import ratpack.handling.Context
import ratpack.handling.Handler
import java.util.concurrent.TimeUnit
import javax.inject.Inject
import kotlin.random.Random

class HelloWorldHandler @Inject constructor(
        private val delayGenerator: DelayGenerator
) : Handler {
    override fun handle(context: Context) {

        Single.just("Hello world")
                .delay(
                        delayGenerator.generate(),
                        delayGenerator.units(),
                        Schedulers.trampoline()
                )
                .timeout(8, TimeUnit.SECONDS)
                .subscribe { text ->
                    context.render(text)
                }
    }
}

class DelayGenerator @Inject constructor(private val seedGenerator: SeedGenerator) {
    companion object {
        private const val MIN_DELAY: Long = 0
        private const val MAX_DELAY: Long = 10
    }

    fun generate(): Long {
        val delay = Random(seedGenerator.seed())
                .nextLong(MIN_DELAY, MAX_DELAY)

        println("Generated delay: [$delay]")

        return delay
    }

    fun units(): TimeUnit = TimeUnit.SECONDS
}

interface SeedGenerator {
    fun seed(): Long
}

class TimeSeedGenerator : SeedGenerator {
    override fun seed(): Long = System.currentTimeMillis()
}
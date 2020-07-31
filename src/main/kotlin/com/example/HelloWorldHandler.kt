package com.example

import com.google.inject.Inject
import ratpack.handling.Context
import ratpack.handling.Handler

class HelloWorldHandler @Inject constructor(
    val manualExecutor: ManualExecutor
) : Handler {
    override fun handle(ctx: Context) {
        ctx.render("hello world: ${manualExecutor.response}")
    }
}
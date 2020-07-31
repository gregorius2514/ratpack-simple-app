package com.example

import com.google.inject.Inject
import com.google.inject.Provider
import ratpack.exec.Blocking
import ratpack.exec.Execution

class ManualExecutorProvider @Inject constructor(
    val httpAsync: HttpAsync
) : Provider<ManualExecutor> {
    override fun get(): ManualExecutor {
        println("Manual executor provider")

        var response: String? = null
        Execution.fork()
            .start {
                httpAsync.call()
                    .then {
                        response = it
                    }
            }

        Blocking.get {

            httpAsync.call()
                .then { result ->
                    response = result
                }
        }

        println("response: $response")

        return ManualExecutor(response!!)
    }
}
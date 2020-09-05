package com.example

import com.google.inject.Inject
import com.google.inject.Provider
import ratpack.exec.Blocking

class ManualExecutorProvider @Inject constructor(
    val httpAsync: HttpAsync
) : Provider<ManualExecutor> {
    companion object {
        val list = mutableListOf<ManualExecutor>()
    }

    override fun get(): ManualExecutor {
        println("Manual executor provider")

        Blocking.get {
            httpAsync.call()
                .map {
                    ManualExecutor(it)
                }
                .then {
                    list.add(it)
                }
        }
        println("response: $list")

        return ManualExecutor(list.first().response)
    }
}
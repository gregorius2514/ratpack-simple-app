package com.example

import com.google.inject.Inject
import ratpack.exec.Promise
import ratpack.http.client.HttpClient
import java.net.URI

class HttpAsync @Inject constructor(
    val httpClient: HttpClient
) {

    fun call(): Promise<String> =
        httpClient.get(URI("http://localhost:1500/hello"))
            .map { response -> response.body.text }
}
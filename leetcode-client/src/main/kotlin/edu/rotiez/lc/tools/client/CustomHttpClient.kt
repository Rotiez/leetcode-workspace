package edu.rotiez.lc.tools.client

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody
import java.io.IOException
import java.lang.reflect.Type

class CustomHttpClient {

    private val client = OkHttpClient()

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun <T> post(
        url: String,
        body: RequestBody,
        responseType: Type
    ): T {
        val request = Request.Builder()
            .url(url)
            .post(body)
            .build()

        client.newCall(request).execute().use { response ->
            val responseBody = response.body?.string()
                ?: throw IOException("Empty response body")

            val adapter = moshi.adapter<T>(responseType)
            return adapter.fromJson(responseBody)
                ?: throw IOException("Failed to parse response")
        }
    }
}
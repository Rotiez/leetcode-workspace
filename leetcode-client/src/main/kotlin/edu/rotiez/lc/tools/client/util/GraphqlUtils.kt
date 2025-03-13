package edu.rotiez.lc.tools.client.util

import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object GraphqlUtils {

    private val moshi = Moshi.Builder().add(KotlinJsonAdapterFactory()).build()

    fun prepareRequestBody(
        query: String,
        variables: Map<String, Any> = emptyMap(),
        operationName: String? = null
    ): RequestBody {
        val requestMap = mutableMapOf(
            "query" to query,
            "variables" to variables
        )

        operationName?.let {
            requestMap["operationName"] = it
        }

        val jsonAdapter = moshi.adapter<Map<String, Any>>(Types.newParameterizedType(
            Map::class.java,
            String::class.java,
            Any::class.java
        ))

        return jsonAdapter.toJson(requestMap)
            .toRequestBody("application/json".toMediaType())
    }
}
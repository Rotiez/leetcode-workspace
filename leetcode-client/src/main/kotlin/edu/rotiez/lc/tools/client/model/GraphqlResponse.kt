package edu.rotiez.lc.tools.client.model

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GraphqlResponse<T>(
    val data: T
)
package com.zamelczyk.fourthwall.api

import com.squareup.moshi.Json

data class Pic(
    @Json(name = "id") val id: String,
    @Json(name = "author") val author: String,
    @Json(name = "url") val url: String,
    @Json(name = "download_url") val downloadUrl: String
) {

    val thumbnailUrl: String
        get() = "https://picsum.photos/id/$id/200/200"
}
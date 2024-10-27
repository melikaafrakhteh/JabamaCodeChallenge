package com.jabama.network.model

import com.google.gson.annotations.SerializedName

data class ResponseAccessTokenDto(
    @SerializedName("access_token")
    val accessToken: String,

    @SerializedName("token_type")
    val tokenType: String
)
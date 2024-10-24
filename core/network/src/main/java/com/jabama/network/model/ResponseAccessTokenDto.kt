package com.jabama.network.model

import com.google.gson.annotations.SerializedName

data class ResponseAccessTokenDto(
    @SerializedName("access_token")
    var accessToken: String,

    @SerializedName("token_type")
    var tokenType: String
)
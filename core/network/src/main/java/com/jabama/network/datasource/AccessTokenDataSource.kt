package com.jabama.network.datasource

import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto

interface AccessTokenDataSource {

    suspend fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ): ResponseAccessTokenDto

}
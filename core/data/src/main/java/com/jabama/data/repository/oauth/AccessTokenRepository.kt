package com.jabama.data.repository.oauth

import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken

interface AccessTokenRepository {

    suspend fun accessToken(
        requestAccessToken: RequestAccessToken
    ): ResponseAccessToken

}
package com.jabama.network.datasource

import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto
import kotlinx.coroutines.Deferred

interface AccessTokenDataSource {

    fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ): Deferred<ResponseAccessTokenDto>

}
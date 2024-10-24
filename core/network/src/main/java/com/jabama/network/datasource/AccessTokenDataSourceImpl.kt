package com.jabama.network.datasource

import com.jabama.network.api.AccessTokenService
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto
import kotlinx.coroutines.Deferred

class AccessTokenDataSourceImpl(
    private val accessTokenService: AccessTokenService
) : AccessTokenDataSource {

    override fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ):Deferred<ResponseAccessTokenDto> = accessTokenService.accessToken(requestAccessToken)

}
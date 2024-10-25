package com.jabama.network.datasource

import com.jabama.common.Resource
import com.jabama.network.api.AccessTokenService
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto

class AccessTokenDataSourceImpl(
    private val accessTokenService: AccessTokenService
) : AccessTokenDataSource {

    override suspend fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ): Resource<ResponseAccessTokenDto> = accessTokenService.accessToken(requestAccessToken)

}
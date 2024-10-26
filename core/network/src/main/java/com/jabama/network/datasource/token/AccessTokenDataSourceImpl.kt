package com.jabama.network.datasource.token

import com.jabama.common.Resource
import com.jabama.network.api.GitHubService
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto

class AccessTokenDataSourceImpl(
    private val service: GitHubService
) : AccessTokenDataSource {

    override suspend fun accessToken(
        requestAccessToken: RequestAccessTokenDto
    ): Resource<ResponseAccessTokenDto> = service.accessToken(requestAccessToken)

}
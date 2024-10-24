package com.jabama.domain.aouth

import com.jabama.data.repository.oauth.AccessTokenRepository
import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken

class GetAccessTokenUseCase(
    private val repository: AccessTokenRepository
) {

    suspend operator fun invoke(requestAccessToken: RequestAccessToken): ResponseAccessToken {
        return repository.accessToken(requestAccessToken)
    }

}
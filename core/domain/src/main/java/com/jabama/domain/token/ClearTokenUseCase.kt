package com.jabama.domain.token

import com.jabama.data.repository.token.TokenRepository

class ClearTokenUseCase(
    private val repository: TokenRepository
) {

    operator fun invoke() {
        return repository.clearToken()
    }

}
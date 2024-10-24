package com.jabama.domain.token

import com.jabama.data.repository.token.TokenRepository

class SaveTokenUseCase(
    private val repository: TokenRepository
) {

    operator fun invoke(token: String) {
        repository.saveToken(token)
    }

}
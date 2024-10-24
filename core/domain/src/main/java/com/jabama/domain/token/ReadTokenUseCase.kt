package com.jabama.domain.token

import com.jabama.data.repository.token.TokenRepository

class ReadTokenUseCase(
    private val repository: TokenRepository
) {

    operator fun invoke(): String {
        return repository.readToken()
    }

}
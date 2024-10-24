package com.jabama.data.mapper

import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto

fun RequestAccessTokenDto.toDomainModel(): RequestAccessToken {
    return RequestAccessToken(
        clientId = clientId,
        clientSecret = clientSecret,
        code = code,
        redirectUri = redirectUri,
        state = state
    )
}

fun RequestAccessToken.toDataModel(): RequestAccessTokenDto {
    return RequestAccessTokenDto(
        clientId = clientId,
        clientSecret = clientSecret,
        code = code,
        redirectUri = redirectUri,
        state = state
    )
}

fun ResponseAccessTokenDto.toDomainModel(): ResponseAccessToken {
    return ResponseAccessToken(
        accessToken = accessToken,
        tokenType = tokenType
    )
}

fun ResponseAccessToken.toDataModel(): ResponseAccessTokenDto {
    return ResponseAccessTokenDto(
        accessToken = accessToken,
        tokenType = tokenType
    )
}
package com.jabama.network.api

import com.jabama.common.Resource
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto
import retrofit2.http.Body
import retrofit2.http.Headers
import retrofit2.http.POST

interface AccessTokenService {

    @Headers("Accept:application/json")
    @POST("https://github.com/login/oauth/access_token")
    suspend fun accessToken(
        @Body requestAccessToken: RequestAccessTokenDto
    ) : Resource<ResponseAccessTokenDto>

}
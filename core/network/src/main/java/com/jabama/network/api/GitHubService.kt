package com.jabama.network.api

import com.jabama.common.Resource
import com.jabama.network.model.RepositoryResponseDto
import com.jabama.network.model.RequestAccessTokenDto
import com.jabama.network.model.ResponseAccessTokenDto
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface GitHubService {

    @POST("login/oauth/access_token")
    suspend fun accessToken(
        @Body requestAccessToken: RequestAccessTokenDto
    ): Resource<ResponseAccessTokenDto>

    @GET("/search/repositories")
    suspend fun getRepositories(
        @Query("q") searchQuery: String
    ): Resource<RepositoryResponseDto>

}
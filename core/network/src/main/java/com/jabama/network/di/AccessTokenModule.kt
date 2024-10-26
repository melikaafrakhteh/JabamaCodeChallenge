package com.jabama.network.di

import com.jabama.network.api.GitHubService
import com.jabama.network.datasource.token.AccessTokenDataSource
import com.jabama.network.datasource.token.AccessTokenDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {

    factory<GitHubService> {
        get<Retrofit>(named(RETROFIT)).create(GitHubService::class.java)
    }

    factory<AccessTokenDataSource> {
        AccessTokenDataSourceImpl(get())
    }
}
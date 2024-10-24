package com.jabama.network.di

import com.jabama.network.api.AccessTokenService
import com.jabama.network.datasource.AccessTokenDataSourceImpl
import org.koin.core.qualifier.named
import org.koin.dsl.module
import retrofit2.Retrofit

val accessTokenModule = module {
    factory {
        get<Retrofit>(named(RETROFIT)).create(AccessTokenService::class.java)
    }

    factory {
        AccessTokenDataSourceImpl(get())
    }
}
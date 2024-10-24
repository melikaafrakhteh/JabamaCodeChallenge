package com.jabama.data.di

import android.content.Context.MODE_PRIVATE
import com.jabama.common.DispatcherQualifier
import com.jabama.common.JabamaDispatchers
import com.jabama.data.repository.AccessTokenRepositoryImpl
import com.jabama.data.repository.token.TokenRepositoryImpl
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val dataModule = module {

    single {
        androidApplication().getSharedPreferences(
            "JABAMA_PREF",
            MODE_PRIVATE
        )
    }

    factory {
        AccessTokenRepositoryImpl(
            get(),
            get(DispatcherQualifier(JabamaDispatchers.DEFAULT))
        )
    }

    factory {
        TokenRepositoryImpl(
            get()
        )
    }

}
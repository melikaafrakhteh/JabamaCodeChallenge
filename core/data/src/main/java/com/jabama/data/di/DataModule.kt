package com.jabama.data.di

import androidx.preference.PreferenceManager
import com.jabama.common.DispatcherQualifier
import com.jabama.common.JabamaDispatchers
import com.jabama.data.repository.oauth.AccessTokenRepositoryImpl
import com.jabama.data.repository.token.TokenRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    single {
        PreferenceManager.getDefaultSharedPreferences(get())
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
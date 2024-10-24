package com.jabama.data.di

import com.jabama.common.DispatcherQualifier
import com.jabama.common.JabamaDispatchers
import com.jabama.data.repository.AccessTokenRepositoryImpl
import org.koin.dsl.module

val dataModule = module {

    factory {
        AccessTokenRepositoryImpl(
            get(),
            get(DispatcherQualifier(JabamaDispatchers.DEFAULT))
        )
    }

}
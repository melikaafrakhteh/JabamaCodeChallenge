package com.jabama.challenge.app

import android.app.Application
import androidx.preference.PreferenceManager
import com.jabama.challenge.repository.token.TokenRepositoryImpl
import com.jabama.common.di.dispatchersModule
import com.jabama.network.di.accessTokenModule
import com.jabama.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.core.qualifier.named
import org.koin.dsl.module
const val APPLICATION_CONTEXT = "APPLICATION_CONTEXT"
class App : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@App)
            modules(
                listOf(
                    appModule,
                    networkModule,
                    accessTokenModule,
                    dispatchersModule,
                )
            )
        }
    }

    val appModule = module {
        factory { TokenRepositoryImpl(get()) }
        single(named(APPLICATION_CONTEXT)) { applicationContext }
        single { PreferenceManager.getDefaultSharedPreferences(get()) }
    }

}
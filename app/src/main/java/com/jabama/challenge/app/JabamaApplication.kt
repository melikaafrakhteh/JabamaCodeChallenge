package com.jabama.challenge.app

import android.app.Application
import com.jabama.common.di.dispatchersModule
import com.jabama.data.di.dataModule
import com.jabama.network.di.accessTokenModule
import com.jabama.network.di.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class JabamaApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {

            androidContext(this@JabamaApplication)

            modules(
                listOf(
                    networkModule,
                    accessTokenModule,
                    dispatchersModule,
                    dataModule,
                )
            )
        }
    }
}
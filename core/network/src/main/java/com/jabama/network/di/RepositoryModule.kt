package com.jabama.network.di

import com.jabama.network.datasource.repository.RepositoryDataSource
import com.jabama.network.datasource.repository.RepositoryDataSourceImpl
import org.koin.dsl.module

val repositoriesModule = module {

    factory<RepositoryDataSource> {
        RepositoryDataSourceImpl(get())
    }

}
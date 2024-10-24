package com.jabama.data.repository

import com.jabama.data.mapper.toDataModel
import com.jabama.data.mapper.toDomainModel
import com.jabama.model.RequestAccessToken
import com.jabama.model.ResponseAccessToken
import com.jabama.network.datasource.AccessTokenDataSource
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class AccessTokenRepositoryImpl(
    private val dataSource: AccessTokenDataSource,
    private val defaultDispatcher: CoroutineDispatcher
) : AccessTokenRepository {

    override suspend fun accessToken(
        requestAccessToken: RequestAccessToken
    ): ResponseAccessToken {
        return withContext(defaultDispatcher) {
            dataSource.accessToken(requestAccessToken.toDataModel()).toDomainModel()
        }
    }

}
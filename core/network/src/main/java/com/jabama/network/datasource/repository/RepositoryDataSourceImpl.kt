package com.jabama.network.datasource.repository

import com.jabama.common.Resource
import com.jabama.network.api.GitHubService
import com.jabama.network.model.RepositoryResponseDto
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class RepositoryDataSourceImpl(
    private val service: GitHubService
) : RepositoryDataSource {

    override fun getRepositories(
        searchQuery: String
    ): Flow<Resource<RepositoryResponseDto>> = flow {
        emit(service.getRepositories(searchQuery))
    }

}
package com.jabama.data.repository.repositories

import com.jabama.common.Resource
import com.jabama.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

interface RepositoriesSearchRepository {

    fun getRepositories(
        searchQuery: String
    ): Flow<Resource<RepositoryResponse>>

}
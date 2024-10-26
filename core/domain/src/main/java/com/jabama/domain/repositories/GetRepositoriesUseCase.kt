package com.jabama.domain.repositories

import com.jabama.common.Resource
import com.jabama.data.repository.repositories.RepositoriesSearchRepository
import com.jabama.model.RepositoryResponse
import kotlinx.coroutines.flow.Flow

class GetRepositoriesUseCase(
    private val repository: RepositoriesSearchRepository
) {

    operator fun invoke(
        query: String
    ): Flow<Resource<RepositoryResponse>> {
        return repository.getRepositories(searchQuery = query)
    }

}
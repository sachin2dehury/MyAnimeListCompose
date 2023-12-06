package github.sachin2dehury.myanimelistcompose.data.repository

import github.sachin2dehury.myanimelistcompose.data.model.PaginatedRemoteModel

interface PaginatedRepository {
    suspend fun getPaginatedList(
        page: Int,
        limit: Int,
        query: String?,
        sortingBasis: String?,
        type: String?,
        rating: String?,
    ): PaginatedRemoteModel
}

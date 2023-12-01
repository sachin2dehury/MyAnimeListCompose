package github.sachin2dehury.myanimelistcompose.data.repository

import github.sachin2dehury.myanimelistcompose.data.AnimeService

class PaginatedRepositoryImpl(private val service: AnimeService) : PaginatedRepository {
    override suspend fun getPaginatedList(
        page: Int,
        limit: Int,
        query: String?
    ) = service.getPaginatedList(page, limit, query)
}
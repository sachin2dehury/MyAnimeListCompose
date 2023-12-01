package github.sachin2dehury.myanimelistcompose.data.repository

import github.sachin2dehury.myanimelistcompose.data.AnimeService

class DetailRepositoryImpl(private val service: AnimeService) : DetailRepository {
    override suspend fun getDetails(id: Int) = service.getDetail(id)
}
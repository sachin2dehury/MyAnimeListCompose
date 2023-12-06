package github.sachin2dehury.myanimelistcompose.data.repository

import github.sachin2dehury.myanimelistcompose.data.model.DetailRemoteModel

interface DetailRepository {
    suspend fun getDetails(id: Int): DetailRemoteModel
}

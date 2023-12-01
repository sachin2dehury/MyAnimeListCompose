package github.sachin2dehury.myanimelistcompose.domain.usecase

import github.sachin2dehury.myanimelistcompose.domain.ResultType
import github.sachin2dehury.myanimelistcompose.domain.model.DetailModel
import kotlinx.coroutines.flow.Flow

interface DetailUseCase {
    suspend operator fun invoke(id: Int): Flow<ResultType<DetailModel>>
}
package github.sachin2dehury.myanimelistcompose.domain.usecase

import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepository
import github.sachin2dehury.myanimelistcompose.domain.ResultType
import github.sachin2dehury.myanimelistcompose.domain.toDetailModel
import kotlinx.coroutines.flow.flow

class DetailUseCase(private val repository: DetailRepository) {
    suspend fun invoke(id: Int) = flow {
        emit(ResultType.Loading)
        try {
            val response = repository.getDetails(id)
            if (response.error.isNullOrEmpty() && response.data != null) {
                emit(ResultType.Success(response.data.toDetailModel()))
            } else {
                emit(
                    ResultType.Error(
                        response.messages?.values?.joinToString() ?: "Something went wrong!!",
                    ),
                )
            }
        } catch (e: Exception) {
            emit(ResultType.Error(e.localizedMessage ?: "Something went wrong!!"))
        }
    }
}

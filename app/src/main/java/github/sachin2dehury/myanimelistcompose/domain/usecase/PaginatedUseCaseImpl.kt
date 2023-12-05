package github.sachin2dehury.myanimelistcompose.domain.usecase

import androidx.paging.PagingSource
import github.sachin2dehury.myanimelistcompose.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel
import github.sachin2dehury.myanimelistcompose.domain.toPaginatedModel

class PaginatedUseCaseImpl(private val repository: PaginatedRepository) : PaginatedUseCase {

    override suspend fun invoke(
        page: Int,
        limit: Int,
        query: String?,
        sortingBasis: String?,
        type: String?,
        rating: String?
    ): PagingSource.LoadResult<Int, PaginatedModel> {
        return try {
            val response =
                repository.getPaginatedList(page, limit, query, sortingBasis, type, rating)
            if (response.error.isNullOrEmpty() && !response.data.isNullOrEmpty()) {
                val nextPage = if (response.pagination?.hasNextPage == true) page + 1 else null
                PagingSource.LoadResult.Page(
                    response.data.filterNotNull().map { it.toPaginatedModel() }, null, nextPage
                )
            } else {
                PagingSource.LoadResult.Error(
                    Throwable(
                        response.messages?.values?.joinToString() ?: "Something went wrong!!"
                    )
                )
            }
        } catch (e: Exception) {
            PagingSource.LoadResult.Error(e)
        }
    }
}
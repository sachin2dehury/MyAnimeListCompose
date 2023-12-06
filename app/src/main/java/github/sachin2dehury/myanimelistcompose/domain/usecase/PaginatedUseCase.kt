package github.sachin2dehury.myanimelistcompose.domain.usecase

import androidx.paging.PagingSource.LoadResult
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel

interface PaginatedUseCase {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        query: String?,
        sortingBasis: String?,
        type: String?,
        rating: String?,
    ): LoadResult<Int, PaginatedModel>
}

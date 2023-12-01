package github.sachin2dehury.myanimelistcompose.domain.usecase

import androidx.paging.PagingSource.LoadResult
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel

interface PaginatedUseCase {
    suspend operator fun invoke(
        page: Int,
        limit: Int,
        query: String?
    ): LoadResult<Int, PaginatedModel>
}
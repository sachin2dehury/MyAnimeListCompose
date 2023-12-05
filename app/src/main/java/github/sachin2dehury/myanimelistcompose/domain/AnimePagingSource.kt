package github.sachin2dehury.myanimelistcompose.domain

import androidx.paging.PagingSource
import androidx.paging.PagingState
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel
import github.sachin2dehury.myanimelistcompose.domain.usecase.PaginatedUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class AnimePagingSource(private val useCase: PaginatedUseCase, private val filter: FilterModel) :
    PagingSource<Int, PaginatedModel>() {
    override fun getRefreshKey(state: PagingState<Int, PaginatedModel>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>) = withContext(Dispatchers.IO) {
        val page = params.key.orZero()
        val limit = params.loadSize.orZero()
        useCase.invoke(page, limit, filter.query, filter.sortingBasis, filter.type, filter.rating)
    }
}
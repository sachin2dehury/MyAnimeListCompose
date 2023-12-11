package github.sachin2dehury.myanimelistcompose.presentation.paginated

import github.sachin2dehury.myanimelistcompose.domain.FilterModel
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.FilterType
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.FilterUtil
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.PaginatedUiState

fun PaginatedUiState.toFilterModel() = FilterModel(
    query,
    FilterUtil.getApiKey(FilterType.SORTING_BASIS, sortingBasis),
    FilterUtil.getApiKey(FilterType.TYPE, type),
    FilterUtil.getApiKey(FilterType.RATING, rating),
)

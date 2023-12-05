package github.sachin2dehury.myanimelistcompose.presentation.paginated

import github.sachin2dehury.myanimelistcompose.domain.FilterModel
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.PaginatedUiState

fun PaginatedUiState.toFilterModel() = FilterModel(
    query,
    FilterUiModel.SORTING_BASIS[sortingBasis],
    FilterUiModel.TYPE[type],
    FilterUiModel.RATING[rating]
)
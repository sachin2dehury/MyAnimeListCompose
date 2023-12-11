package github.sachin2dehury.myanimelistcompose.presentation.paginated.model

data class PaginatedUiState(
    val query: String? = null,
    val sortingBasis: String = FilterUtil.DEFAULT,
    val type: String = FilterUtil.DEFAULT,
    val rating: String = FilterUtil.DEFAULT,
)

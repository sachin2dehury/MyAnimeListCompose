package github.sachin2dehury.myanimelistcompose.presentation.paginated.model

data class PaginatedUiState(
    val query: String? = null,
    val sortingBasis: String = "Sorting basis",
    val type: String = "Type",
    val rating: String = "Rating"
)

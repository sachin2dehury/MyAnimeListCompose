package github.sachin2dehury.myanimelistcompose.domain

data class FilterModel(
    val query: String? = null,
    val sortingBasis: String? = null,
    val type: String? = null,
    val rating: String? = null,
)

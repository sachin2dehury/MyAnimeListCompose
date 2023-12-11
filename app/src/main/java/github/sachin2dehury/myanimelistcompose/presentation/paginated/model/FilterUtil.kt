package github.sachin2dehury.myanimelistcompose.presentation.paginated.model

object FilterUtil {

    const val DEFAULT = "Default"

    private val RATING = mapOf(
        DEFAULT to null,
        "G - All Ages" to "g",
        "PG - Children" to "pg",
        "PG-13 - Teens 13 or older" to "pg13",
        "R - 17+ (violence & profanity)" to "r17",
        "R+ - Mild Nudity" to "r",
        "Rx - Hentai" to "rx",
    )

    private val SORTING_BASIS = mapOf(
        DEFAULT to null,
        "Id" to "mal_id",
        "Name" to "title",
        "Episodes" to "episodes",
        "Rank" to "rank",
        "Rating" to "score",
    )

    private val TYPE = mapOf(
        DEFAULT to null,
        "Tv" to "tv",
        "Movie" to "movie",
        "Ova" to "ova",
        "Special" to "special",
        "ONA" to "ona",
        "Music" to "music",
    )

    val filterList = listOf(FilterType.SORTING_BASIS, FilterType.TYPE, FilterType.RATING)

    fun getValues(@FilterType filterType: String) = when (filterType) {
        FilterType.RATING -> RATING
        FilterType.TYPE -> TYPE
        FilterType.SORTING_BASIS -> SORTING_BASIS
        else -> throw Exception("Unknown Filter Type")
    }.keys.toList()

    fun getStateValue(
        @FilterType filterType: String,
        state: PaginatedUiState,
        showDefault: Boolean = true,
    ): String {
        val result = when (filterType) {
            FilterType.RATING -> state.rating
            FilterType.TYPE -> state.type
            FilterType.SORTING_BASIS -> state.sortingBasis
            else -> throw Exception("Unknown Filter Type $filterType")
        }
        return if (result == DEFAULT && !showDefault) filterType else result
    }

    fun updateFilterState(
        @FilterType filterType: String,
        state: PaginatedUiState,
        selection: String,
    ) = when (filterType) {
        FilterType.RATING -> state.copy(rating = selection)
        FilterType.TYPE -> state.copy(type = selection)
        FilterType.SORTING_BASIS -> state.copy(sortingBasis = selection)
        else -> throw Exception("Unknown Filter Type $filterType")
    }

    fun getApiKey(@FilterType filterType: String, selected: String) = when (filterType) {
        FilterType.RATING -> RATING[selected]
        FilterType.TYPE -> TYPE[selected]
        FilterType.SORTING_BASIS -> SORTING_BASIS[selected]
        else -> throw Exception("Unknown Filter Type $filterType")
    }
}

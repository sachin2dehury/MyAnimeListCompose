package github.sachin2dehury.myanimelistcompose.presentation.paginated

object FilterUiModel {

    val RATING = mapOf(
        "Rating" to null,
        "G - All Ages" to "g",
        "PG - Children" to "pg",
        "PG-13 - Teens 13 or older" to "pg13",
        "R - 17+ (violence & profanity)" to "r17",
        "R+ - Mild Nudity" to "r",
        "Rx - Hentai" to "rx"
    )

    val SORTING_BASIS = mapOf(
        "Sorting basis" to null,
        "Id" to "mal_id",
        "Name" to "title",
        "Episodes" to "episodes",
        "Rank" to "rank",
        "Rating" to "score"
    )

    val TYPE = mapOf(
        "Type" to null,
        "Tv" to "tv",
        "Movie" to "movie",
        "Ova" to "ova",
        "Special" to "special",
        "ONA" to "ona",
        "Music" to "music"
    )
}
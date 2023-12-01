package github.sachin2dehury.myanimelistcompose.domain.model

data class PaginatedModel(
    val duration: String,
    val episodes: Int,
    val images: String,
    val malId: Int,
    val rank: Int,
    val score: Float,
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
)
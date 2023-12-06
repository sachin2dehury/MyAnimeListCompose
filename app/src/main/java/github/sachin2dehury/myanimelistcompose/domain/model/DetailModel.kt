package github.sachin2dehury.myanimelistcompose.domain.model

data class DetailModel(
    val aired: String,
    val background: String,
    val duration: String,
    val episodes: Int,
    val genres: List<String>,
    val images: String,
    val malId: Int,
    val rank: Int,
    val rating: String,
    val score: Float,
    val synopsis: String,
    val openingTheme: List<String>,
    val endingTheme: List<String>,
    val title: String,
    val titleEnglish: String,
    val titleJapanese: String,
)

package github.sachin2dehury.myanimelistcompose.domain

import github.sachin2dehury.myanimelistcompose.data.model.DetailRemoteModel
import github.sachin2dehury.myanimelistcompose.data.model.PaginatedRemoteModel
import github.sachin2dehury.myanimelistcompose.domain.model.DetailModel
import github.sachin2dehury.myanimelistcompose.domain.model.PaginatedModel

fun PaginatedRemoteModel.Data.toPaginatedModel() = PaginatedModel(
    duration.orEmpty(),
    episodes.orZero(),
    images?.webp?.largeImageUrl.orEmpty(),
    malId.orZero(),
    rank.orZero(),
    score.orZero(),
    title.orEmpty(),
    titleEnglish.orEmpty(),
    titleJapanese.orEmpty()
)

fun DetailRemoteModel.Data.toDetailModel() = DetailModel(
    aired?.prop?.string.orEmpty(),
    background.orEmpty(),
    duration.orEmpty(),
    episodes.orZero(),
    genres.orEmpty().map { it?.name.orEmpty() },
    images?.webp?.largeImageUrl.orEmpty(),
    malId.orZero(),
    rank.orZero(),
    rating.orEmpty(),
    score.orZero(),
    synopsis.orEmpty(),
    theme?.openings.orEmpty().filterNotNull(),
    theme?.endings.orEmpty().filterNotNull(),
    title.orEmpty(),
    titleEnglish.orEmpty(),
    titleJapanese.orEmpty()
)

fun Int?.orZero() = this ?: 0
fun Float?.orZero() = this ?: 0f
package github.sachin2dehury.myanimelistcompose.presentation.paginated.model

import androidx.annotation.StringDef

@Retention(AnnotationRetention.SOURCE)
@StringDef(
    FilterType.SORTING_BASIS,
    FilterType.TYPE,
    FilterType.RATING,
)
annotation class FilterType {
    companion object {
        const val SORTING_BASIS = "Sorting basis"
        const val TYPE = "Type"
        const val RATING = "Rating"
    }
}

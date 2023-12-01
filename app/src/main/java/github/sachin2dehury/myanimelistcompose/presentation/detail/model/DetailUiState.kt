package github.sachin2dehury.myanimelistcompose.presentation.detail.model

import github.sachin2dehury.myanimelistcompose.domain.model.DetailModel

data class DetailUiState(
    val isLoading: Boolean = false,
    val data: DetailModel? = null,
    val error: String? = null
)

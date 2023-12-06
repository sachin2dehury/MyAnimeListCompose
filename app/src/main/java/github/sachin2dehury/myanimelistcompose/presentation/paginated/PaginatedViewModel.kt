package github.sachin2dehury.myanimelistcompose.presentation.paginated

import androidx.lifecycle.ViewModel
import androidx.paging.Pager
import androidx.paging.PagingConfig
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.myanimelistcompose.domain.AnimePagingSource
import github.sachin2dehury.myanimelistcompose.domain.usecase.PaginatedUseCase
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.PaginatedUiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import javax.inject.Inject

@HiltViewModel
class PaginatedViewModel @Inject constructor(useCase: PaginatedUseCase) : ViewModel() {

    private val pagingConfig = PagingConfig(8)

    private val _state = MutableStateFlow(PaginatedUiState())
    val state = _state.asStateFlow()

    val pager = _state.flatMapLatest {
        Pager(pagingConfig, 1) {
            AnimePagingSource(useCase, _state.value.toFilterModel())
        }.flow
    }

    fun updateState(paginatedUiState: PaginatedUiState) {
        _state.value = paginatedUiState
    }
}

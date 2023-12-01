package github.sachin2dehury.myanimelistcompose.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import github.sachin2dehury.myanimelistcompose.domain.ResultType
import github.sachin2dehury.myanimelistcompose.domain.usecase.DetailUseCase
import github.sachin2dehury.myanimelistcompose.presentation.detail.model.DetailUiState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.plus
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val useCase: DetailUseCase) : ViewModel() {

    private val _state = MutableStateFlow(DetailUiState())
    val state = _state.asStateFlow()

    fun getDetail(id: Int) = viewModelScope.launch(Dispatchers.IO) {
        useCase.invoke(id).onEach { result ->
            when (result) {
                is ResultType.Error -> {
                    _state.update {
                        it.copy(isLoading = false, error = result.message)
                    }
                }

                ResultType.Loading -> {
                    _state.update {
                        it.copy(isLoading = true, error = null)
                    }
                }

                is ResultType.Success -> {
                    _state.update {
                        it.copy(isLoading = false, error = null, data = result.data)
                    }
                }
            }
        }.launchIn(viewModelScope + Dispatchers.IO)
    }
}
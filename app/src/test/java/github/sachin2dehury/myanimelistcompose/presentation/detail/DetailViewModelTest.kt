package github.sachin2dehury.myanimelistcompose.presentation.detail

import androidx.compose.ui.res.pluralStringResource
import com.google.common.truth.Truth.assertThat
import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepository
import github.sachin2dehury.myanimelistcompose.data.repository.FakeDetailRepository
import github.sachin2dehury.myanimelistcompose.domain.usecase.DetailUseCase
import github.sachin2dehury.myanimelistcompose.presentation.detail.model.DetailUiState
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.plus
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var viewModel: DetailViewModel
    private lateinit var useCase: DetailUseCase
    private lateinit var repository: DetailRepository

    @Before
    fun setUp() {
        repository = FakeDetailRepository()
        useCase = DetailUseCase(repository)
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `validate initial loading state`() {
        runBlocking {
            viewModel.getDetail(0).join()
            val result = viewModel.state.first()
            assertThat(result.isLoading).isFalse()
        }
    }

//    @Test
//    fun `validate loading state`() {
//        runBlocking {
//            viewModel.getDetail(0)
//            val result = viewModel.state.value
//            println(result)
//            assertThat(result.isLoading).isTrue()
//        }
//    }
}

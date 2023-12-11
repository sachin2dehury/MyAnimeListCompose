package github.sachin2dehury.myanimelistcompose.presentation.detail

import com.google.common.truth.Truth.assertThat
import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepository
import github.sachin2dehury.myanimelistcompose.data.repository.FakeDetailRepository
import github.sachin2dehury.myanimelistcompose.domain.usecase.DetailUseCase
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailViewModelTest {
    private lateinit var useCase: DetailUseCase
    private lateinit var repository: DetailRepository
    private lateinit var viewModel: DetailViewModel

    @Before
    fun setUp() {
        repository = FakeDetailRepository()
        useCase = DetailUseCase(repository)
        viewModel = DetailViewModel(useCase)
    }

    @Test
    fun `validate initial loading state`() {
        runBlocking {
            val result = viewModel.state.first()
            assertThat(result.isLoading).isFalse()
        }
    }

    @Test
    fun `validate loading state`() {
        runBlocking {
            viewModel.getDetail(0)
            delay((1 until 1000).random().toLong())
            val result = viewModel.state.value
            assertThat(result.isLoading).isTrue()
        }
    }

    @Test
    fun `validate loaded state`() {
        runBlocking {
            viewModel.getDetail(0).invokeOnCompletion {
                val result = viewModel.state.value
                assertThat(result.isLoading).isFalse()
            }
        }
    }
}

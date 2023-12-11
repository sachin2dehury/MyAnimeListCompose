package github.sachin2dehury.myanimelistcompose.presentation.paginated

import com.google.common.truth.Truth.assertThat
import github.sachin2dehury.myanimelistcompose.data.repository.FakePaginatedRepository
import github.sachin2dehury.myanimelistcompose.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelistcompose.domain.usecase.PaginatedUseCase
import github.sachin2dehury.myanimelistcompose.presentation.paginated.model.PaginatedUiState
import org.junit.Before
import org.junit.Test

class PaginatedViewModelTest {
    private lateinit var useCase: PaginatedUseCase
    private lateinit var repository: PaginatedRepository
    private lateinit var viewModel: PaginatedViewModel

    @Before
    fun setUp() {
        repository = FakePaginatedRepository()
        useCase = PaginatedUseCase(repository)
        viewModel = PaginatedViewModel(useCase)
    }

    @Test
    fun `validate initial state`() {
        val result = viewModel.state.value
        assertThat(result == PaginatedUiState()).isTrue()
    }

    @Test
    fun `validate query update`() {
        viewModel.updateState(PaginatedUiState(query = "foo"))
        val result = viewModel.state.value
        assertThat(result == PaginatedUiState().copy(query = "foo")).isTrue()
    }

    @Test
    fun `validate sorting update`() {
        viewModel.updateState(PaginatedUiState(sortingBasis = "foo"))
        val result = viewModel.state.value
        assertThat(result == PaginatedUiState().copy(sortingBasis = "foo")).isTrue()
    }

    @Test
    fun `validate rating update`() {
        viewModel.updateState(PaginatedUiState(rating = "foo"))
        val result = viewModel.state.value
        assertThat(result == PaginatedUiState().copy(rating = "foo")).isTrue()
    }

    @Test
    fun `validate type update`() {
        viewModel.updateState(PaginatedUiState(type = "foo"))
        val result = viewModel.state.value
        assertThat(result == PaginatedUiState().copy(type = "foo")).isTrue()
    }
}

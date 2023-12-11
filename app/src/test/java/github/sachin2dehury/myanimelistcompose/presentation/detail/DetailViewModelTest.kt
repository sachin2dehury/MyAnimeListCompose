package github.sachin2dehury.myanimelistcompose.presentation.detail

import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepository
import github.sachin2dehury.myanimelistcompose.data.repository.FakeDetailRepository
import github.sachin2dehury.myanimelistcompose.domain.usecase.DetailUseCase
import org.junit.Before

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

//    @Test
//    fun `validate initial loading state`() {
//        runBlocking {
//            viewModel.getDetail(0).join()
//            val result = viewModel.state.first()
//            assertThat(result.isLoading).isFalse()
//        }
//    }

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

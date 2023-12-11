package github.sachin2dehury.myanimelistcompose.domain.usecase

import com.google.common.truth.Truth
import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepository
import github.sachin2dehury.myanimelistcompose.data.repository.FakeDetailRepository
import github.sachin2dehury.myanimelistcompose.domain.ResultType
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.toList
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class DetailUseCaseTest {
    private lateinit var useCase: DetailUseCase
    private lateinit var detailRepository: DetailRepository

    @Before
    fun setUp() {
        detailRepository = FakeDetailRepository()
        useCase = DetailUseCase(detailRepository)
    }

    @Test
    fun `validate loading`() {
        runBlocking {
            val result = useCase.invoke(0).first()
            Truth.assertThat(result is ResultType.Loading)
        }
    }

    @Test
    fun `validate not loading`() {
        runBlocking {
            val result = useCase.invoke(0).toList()[1]
            Truth.assertThat(result !is ResultType.Loading)
        }
    }

    @Test
    fun `validate error msg`() {
        runBlocking {
            when (val result = useCase.invoke(0).toList()[1]) {
                is ResultType.Error -> {
                    Truth.assertThat(result.message.isNotEmpty())
                }

                ResultType.Loading -> {
                    Truth.assertThat(false)
                }

                is ResultType.Success -> {
                    Truth.assertThat(true)
                }
            }
        }
    }

    @Test
    fun `validate success`() {
        runBlocking {
            when (val result = useCase.invoke(0).toList()[1]) {
                is ResultType.Error -> {
                    Truth.assertThat(true)
                }

                ResultType.Loading -> {
                    Truth.assertThat(false)
                }

                is ResultType.Success -> {
                    Truth.assertThat(result.data.malId >= 0)
                }
            }
        }
    }

    @Test
    fun `validate flow emit`() {
        runBlocking {
            val result = useCase.invoke(0).toList()
            Truth.assertThat(result.size < 2)
        }
    }
}

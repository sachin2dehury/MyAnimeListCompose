package github.sachin2dehury.myanimelistcompose.domain.usecase

import com.google.common.truth.Truth.assertThat
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
    private lateinit var repository: DetailRepository

    @Before
    fun setUp() {
        repository = FakeDetailRepository()
        useCase = DetailUseCase(repository)
    }

    @Test
    fun `validate loading`() {
        runBlocking {
            val result = useCase.invoke(0).first()
            assertThat(result is ResultType.Loading)
        }
    }

    @Test
    fun `validate not loading`() {
        runBlocking {
            val result = useCase.invoke(0).toList()[1]
            assertThat(result is ResultType.Loading).isFalse()
        }
    }

    @Test
    fun `validate error msg`() {
        runBlocking {
            when (val result = useCase.invoke(0).toList()[1]) {
                is ResultType.Error -> {
                    assertThat(result.message.isNotEmpty()).isTrue()
                }

                else -> {
                    assertThat(result is ResultType.Loading).isFalse()
                }
            }
        }
    }

    @Test
    fun `validate success`() {
        runBlocking {
            when (val result = useCase.invoke(0).toList()[1]) {
                is ResultType.Success -> {
                    assertThat(result.data.malId >= 0).isTrue()
                }

                else -> {
                    assertThat(result is ResultType.Loading).isFalse()
                }
            }
        }
    }

    @Test
    fun `validate flow emit`() {
        runBlocking {
            val result = useCase.invoke(0).toList()
            assertThat(result.size == 2).isTrue()
        }
    }
}

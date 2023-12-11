package github.sachin2dehury.myanimelistcompose.domain.usecase

import androidx.paging.PagingSource
import com.google.common.truth.Truth.assertThat
import github.sachin2dehury.myanimelistcompose.data.repository.FakePaginatedRepository
import github.sachin2dehury.myanimelistcompose.data.repository.PaginatedRepository
import kotlinx.coroutines.runBlocking
import org.junit.Before
import org.junit.Test

class PaginatedUseCaseTest {
    private lateinit var useCase: PaginatedUseCase
    private lateinit var repository: PaginatedRepository

    @Before
    fun setUp() {
        repository = FakePaginatedRepository()
        useCase = PaginatedUseCase(repository)
    }

    @Test
    fun `validate page result is not invalid`() {
        runBlocking {
            val result = useCase.invoke(0, 1, null, null, null, null)
            assertThat(result is PagingSource.LoadResult.Invalid).isFalse()
        }
    }

    @Test
    fun `validate page error has message`() {
        runBlocking {
            val result = useCase.invoke(0, 1, null, null, null, null)
            when (result) {
                is PagingSource.LoadResult.Error -> {
                    assertThat(result.throwable.localizedMessage.isNullOrEmpty()).isFalse()
                }

                else -> {
                    assertThat(result is PagingSource.LoadResult.Invalid).isFalse()
                }
            }
        }
    }

    @Test
    fun `validate page has no prev key`() {
        runBlocking {
            when (val result = useCase.invoke(0, 1, null, null, null, null)) {
                is PagingSource.LoadResult.Page -> {
                    assertThat(result.prevKey == null).isTrue()
                }

                else -> {
                    assertThat(result is PagingSource.LoadResult.Invalid).isFalse()
                }
            }
        }
    }

    @Test
    fun `validate page has non empty list`() {
        runBlocking {
            when (val result = useCase.invoke(0, 1, null, null, null, null)) {
                is PagingSource.LoadResult.Page -> {
                    assertThat(result.data.isNotEmpty()).isTrue()
                }

                else -> {
                    assertThat(result is PagingSource.LoadResult.Invalid).isFalse()
                }
            }
        }
    }

    @Test
    fun `validate page has next key`() {
        runBlocking {
            when (val result = useCase.invoke(0, 1, null, null, null, null)) {
                is PagingSource.LoadResult.Page -> {
                    assertThat(result.nextKey != null).isTrue()
                }
                else -> {
                    assertThat(result is PagingSource.LoadResult.Invalid).isFalse()
                }
            }
        }
    }
}

package github.sachin2dehury.myanimelistcompose.domain.usecase

import androidx.paging.PagingSource
import com.google.common.truth.Truth
import github.sachin2dehury.myanimelistcompose.data.repository.FakePaginatedRepository
import github.sachin2dehury.myanimelistcompose.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelistcompose.domain.orZero
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
            Truth.assertThat(result !is PagingSource.LoadResult.Invalid)
        }
    }

    @Test
    fun `validate page error has message`() {
        runBlocking {
            val result = useCase.invoke(0, 1, null, null, null, null)
            when (result) {
                is PagingSource.LoadResult.Error -> {
                    Truth.assertThat(!result.throwable.localizedMessage.isNullOrEmpty())
                }

                is PagingSource.LoadResult.Invalid -> {
                    Truth.assertThat(false)
                }

                is PagingSource.LoadResult.Page -> {
                    Truth.assertThat(true)
                }
            }
        }
    }

    @Test
    fun `validate page has no prev key`() {
        runBlocking {
            val result = useCase.invoke(0, 1, null, null, null, null)
            when (result) {
                is PagingSource.LoadResult.Error -> {
                    Truth.assertThat(true)
                }

                is PagingSource.LoadResult.Invalid -> {
                    Truth.assertThat(false)
                }

                is PagingSource.LoadResult.Page -> {
                    Truth.assertThat(result.prevKey == null)
                }
            }
        }
    }

    @Test
    fun `validate page has non empty list`() {
        runBlocking {
            val result = useCase.invoke(0, 1, null, null, null, null)
            when (result) {
                is PagingSource.LoadResult.Error -> {
                    Truth.assertThat(true)
                }

                is PagingSource.LoadResult.Invalid -> {
                    Truth.assertThat(false)
                }

                is PagingSource.LoadResult.Page -> {
                    Truth.assertThat(result.data.isNotEmpty())
                }
            }
        }
    }

    @Test
    fun `validate page has next key`() {
        runBlocking {
            val result = useCase.invoke(0, 1, null, null, null, null)
            when (result) {
                is PagingSource.LoadResult.Error -> {
                    Truth.assertThat(true)
                }

                is PagingSource.LoadResult.Invalid -> {
                    Truth.assertThat(false)
                }

                is PagingSource.LoadResult.Page -> {
                    Truth.assertThat(result.nextKey.orZero() > 0)
                }
            }
        }
    }
}

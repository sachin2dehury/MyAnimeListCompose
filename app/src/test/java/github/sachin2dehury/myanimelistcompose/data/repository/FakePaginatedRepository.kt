package github.sachin2dehury.myanimelistcompose.data.repository

import github.sachin2dehury.myanimelistcompose.data.model.PaginatedRemoteModel

class FakePaginatedRepository : PaginatedRepository {

    private val models = mutableListOf<PaginatedRemoteModel>()

    private fun setUp() {
        models.add(
            PaginatedRemoteModel(
                status = 400,
                messages = mapOf("Error" to listOf("Test Error")),
                type = "error",
            ),
        )
        models.add(
            PaginatedRemoteModel(
                data = listOf(
                    PaginatedRemoteModel.Data(
                        title = "Something",
                        malId = 231,
                    ),
                ),
                type = "success",
                pagination = PaginatedRemoteModel.Pagination(hasNextPage = true),
            ),
        )
        models.add(
            PaginatedRemoteModel(),
        )
        models.add(
            PaginatedRemoteModel(
                data = listOf(),
                type = "success",
                pagination = PaginatedRemoteModel.Pagination(hasNextPage = false),
            ),
        )
        models.add(
            PaginatedRemoteModel(
                data = listOf(),
                type = "success",
            ),
        )
    }

    init {
        setUp()
    }

    override suspend fun getPaginatedList(
        page: Int,
        limit: Int,
        query: String?,
        sortingBasis: String?,
        type: String?,
        rating: String?,
    ): PaginatedRemoteModel {
        return models.random()
    }
}

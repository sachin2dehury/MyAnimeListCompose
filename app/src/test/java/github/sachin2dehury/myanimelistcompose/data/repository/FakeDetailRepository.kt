package github.sachin2dehury.myanimelistcompose.data.repository

import github.sachin2dehury.myanimelistcompose.data.model.DetailRemoteModel
import kotlinx.coroutines.delay

class FakeDetailRepository : DetailRepository {

    private val models = mutableListOf<DetailRemoteModel>()

    private fun setUp() {
        models.add(
            DetailRemoteModel(
                status = 400,
                messages = mapOf("Error" to listOf("Test Error")),
                type = "error",
            ),
        )
        models.add(
            DetailRemoteModel(
                data = DetailRemoteModel.Data(
                    title = "Something",
                    malId = 231,
                ),
                type = "success",
            ),
        )
        models.add(
            DetailRemoteModel(),
        )
        models.add(
            DetailRemoteModel(
                data = DetailRemoteModel.Data(),
                type = "success",
            ),
        )
    }

    init {
        setUp()
    }

    override suspend fun getDetails(id: Int): DetailRemoteModel {
        delay(1000)
        return models.random()
    }
}

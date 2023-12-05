package github.sachin2dehury.myanimelistcompose.data

import github.sachin2dehury.myanimelistcompose.data.model.DetailRemoteModel
import github.sachin2dehury.myanimelistcompose.data.model.PaginatedRemoteModel
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface AnimeService {

    @GET("v4/anime")
    suspend fun getPaginatedList(
        @Query("page") page: Int,
        @Query("limit") limit: Int,
        @Query("q") query: String?,
        @Query("order_by") sortingBasis: String?,
        @Query("type") type: String?,
        @Query("rating") rating: String?,
    ): PaginatedRemoteModel

    @GET("v4/anime/{id}/full")
    suspend fun getDetail(@Path("id") id: Int): DetailRemoteModel

    companion object {
        const val BASE_URL = "https://api.jikan.moe"
    }
}
package github.sachin2dehury.myanimelistcompose

import com.squareup.moshi.Moshi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import github.sachin2dehury.myanimelistcompose.data.AnimeService
import github.sachin2dehury.myanimelistcompose.data.AnimeService.Companion.BASE_URL
import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepository
import github.sachin2dehury.myanimelistcompose.data.repository.DetailRepositoryImpl
import github.sachin2dehury.myanimelistcompose.data.repository.PaginatedRepository
import github.sachin2dehury.myanimelistcompose.data.repository.PaginatedRepositoryImpl
import github.sachin2dehury.myanimelistcompose.domain.usecase.DetailUseCase
import github.sachin2dehury.myanimelistcompose.domain.usecase.PaginatedUseCase
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    @Singleton
    fun providesOkhttp() = OkHttpClient.Builder().build()

    @Provides
    @Singleton
    fun providesMoshi() = Moshi.Builder().build()

    @Provides
    @Singleton
    fun providesMoshiConverterFactory(moshi: Moshi) = MoshiConverterFactory.create(moshi)

    @Provides
    @Singleton
    fun providesRetrofit(okHttpClient: OkHttpClient, moshiConverterFactory: MoshiConverterFactory) =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
            .addConverterFactory(moshiConverterFactory)
            .build()

    @Provides
    @Singleton
    fun providesService(retrofit: Retrofit) = retrofit.create(AnimeService::class.java)

    @Provides
    @Singleton
    fun providesDetailRepository(service: AnimeService): DetailRepository =
        DetailRepositoryImpl(service)

    @Provides
    @Singleton
    fun providesPaginatedRepository(service: AnimeService): PaginatedRepository =
        PaginatedRepositoryImpl(service)

    @Provides
    @Singleton
    fun providesDetailUseCase(repository: DetailRepository) = DetailUseCase(repository)

    @Provides
    @Singleton
    fun providesPaginatedUseCase(repository: PaginatedRepository) = PaginatedUseCase(repository)
}

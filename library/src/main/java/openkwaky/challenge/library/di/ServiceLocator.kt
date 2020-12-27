package openkwaky.challenge.library.di

import androidx.room.Room
import openkwaky.challenge.library.Analytics
import openkwaky.challenge.library.controller.AnalyticsController
import openkwaky.challenge.library.core.AnalyticsInteractor
import openkwaky.challenge.library.repository.database.LibraryDatabase
import openkwaky.challenge.library.repository.database.TagRepository
import openkwaky.challenge.library.repository.database.transformer.TagTransformer
import openkwaky.challenge.library.repository.datasource.AnalyticsApi
import openkwaky.challenge.library.timer.Timer
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object ServiceLocator {

    fun provideDatabase() = Room.databaseBuilder(
        Analytics.internalAppContext,
        LibraryDatabase::class.java,
        "analytics-challenge-db"
    ).build()

    fun provideAnalyticsController() = AnalyticsController(provideAnalyticsInteractor())

    fun provideAnalyticsInteractor() = AnalyticsInteractor(provideRepository(), provideTimer())

    fun provideRepository() =
        TagRepository(TagTransformer(), provideDatabase(), provideApi())

    fun provideRetrofit() = Retrofit.Builder()
            .baseUrl(Analytics.internalConfiguration.url!!)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    fun provideAnalyticsService() =
        provideRetrofit().create(AnalyticsApi.AnalyticsService::class.java)

    fun provideApi() =
        AnalyticsApi(provideAnalyticsService())

    fun provideTimer() = Timer(provideRepository())
}
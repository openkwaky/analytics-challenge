package openkwaky.challenge.library.di

import androidx.room.Room
import dagger.Module
import dagger.Provides
import openkwaky.challenge.library.Analytics
import openkwaky.challenge.library.repository.database.LibraryDatabase
import openkwaky.challenge.library.repository.datasource.AnalyticsApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AnalyticsModule(analytics: Analytics) {
    @Provides
    internal fun provideDatabase(): LibraryDatabase = Room.databaseBuilder(
        Analytics.internalAppContext,
        LibraryDatabase::class.java,
        "analytics-challenge-db"
    ).build()

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder()
            .baseUrl(Analytics.internalConfiguration.url!!)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun provideAnalyticsService(retrofit: Retrofit): AnalyticsApi.AnalyticsService =
        retrofit.create(AnalyticsApi.AnalyticsService::class.java)

}

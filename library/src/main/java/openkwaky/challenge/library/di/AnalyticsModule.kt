package openkwaky.challenge.library.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import openkwaky.challenge.library.Analytics
import openkwaky.challenge.library.model.Configuration
import openkwaky.challenge.library.repository.database.LibraryDatabase
import openkwaky.challenge.library.repository.datasource.AnalyticsApi
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
class AnalyticsModule(val analytics: Analytics) {
    @Provides
    internal fun provideDatabase(context: Context): LibraryDatabase = Room.databaseBuilder(
        context,
        LibraryDatabase::class.java,
        "analytics-challenge-db"
    ).build()

    @Provides
    fun provideRetrofit(configuration: Configuration): Retrofit = Retrofit.Builder()
            .baseUrl(configuration.url!!)
            .addConverterFactory(MoshiConverterFactory.create())
            .build()

    @Provides
    fun provideAnalyticsService(retrofit: Retrofit): AnalyticsApi.AnalyticsService =
        retrofit.create(AnalyticsApi.AnalyticsService::class.java)

    @Provides
    fun provideConfiguration(): Configuration = analytics.configuration

    @Provides
    fun provideContext(): Context = analytics.appContext
}

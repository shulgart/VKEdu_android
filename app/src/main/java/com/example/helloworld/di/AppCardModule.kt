package com.example.helloworld.di

import android.app.Application
import androidx.room.Room
import com.example.helloworld.data.appcard.AppCardApi
import com.example.helloworld.data.appcard.AppCardDao
import com.example.helloworld.data.appcard.AppCardDatabase
import com.example.helloworld.data.appcard.AppCardMapper
import com.example.helloworld.data.appcard.AppCardRepositoryImpl
import com.example.helloworld.domain.appcard.AppCardRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppCardModule {
    @Provides
    @Singleton
    fun provideAppCardApi(retrofit: Retrofit): AppCardApi {
        return retrofit.create(AppCardApi::class.java)
    }

    @Provides
    @Singleton
    fun provideCardDatabase(app: Application): AppCardDatabase {
        return Room.databaseBuilder(
            app,
            AppCardDatabase::class.java,
            "app-db"
        ).build()
    }

    @Provides
    @Singleton
    fun provideAppCardDao(database: AppCardDatabase): AppCardDao {
        return database.appsDao()
    }

    @Provides
    @Singleton
    fun provideAppCardMapper(): AppCardMapper {
        return AppCardMapper()
    }

    @Provides
    @Singleton
    fun provideAppCardRepository(
        api: AppCardApi,
        dao: AppCardDao,
        mapper: AppCardMapper
    ): AppCardRepository {
        return AppCardRepositoryImpl(api, dao, mapper)
    }
}
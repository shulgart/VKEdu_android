package com.example.helloworld.domain.appcard

import com.example.helloworld.data.appcard.AppCardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

interface AppCardRepository {
    suspend fun get(): List<AppCard>
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindAppCardRepository(
        appsRepositoryImpl: AppCardRepositoryImpl
    ): AppCardRepository
}
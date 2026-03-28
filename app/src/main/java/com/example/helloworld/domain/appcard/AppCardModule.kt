package com.example.helloworld.domain.appcard

import com.example.helloworld.data.appcard.AppCardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class AppCardModule {

    @Binds
    abstract fun bindAppCardRepository(
        appsRepositoryImpl: AppCardRepositoryImpl
    ): AppCardRepository
}
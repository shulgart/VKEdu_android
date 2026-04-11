package com.example.helloworld.domain.appcard

import com.example.helloworld.data.appcard.AppCardRepositoryImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.flow.Flow

interface AppCardRepository {
    suspend fun get(): Flow<List<AppCard>>
}
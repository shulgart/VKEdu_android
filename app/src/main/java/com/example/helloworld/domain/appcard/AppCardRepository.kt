package com.example.helloworld.domain.appcard

interface AppCardRepository {
    suspend fun get(): List<AppCard>
}
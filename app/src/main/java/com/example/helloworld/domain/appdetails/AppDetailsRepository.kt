package com.example.helloworld.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): Flow<AppDetails>

    suspend fun toggleWishlist(id: String)

    fun observeAppDetails(id: String): Flow<AppDetails>
}
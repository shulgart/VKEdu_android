package com.example.helloworld.domain.appdetails

import kotlinx.coroutines.flow.Flow

interface AppDetailsRepository {
    fun getAppDetails(id: String): Flow<AppDetails>
}
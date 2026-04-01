package com.example.helloworld.domain.appdetails

interface AppDetailsRepository {
    suspend fun getAppDetails(id: String): AppDetails
}
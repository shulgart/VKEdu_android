package com.example.helloworld.domain.appdetails

interface AppDetailsRepository {
    suspend fun get(): AppDetails
}
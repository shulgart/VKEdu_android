package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appdetails.AppDetails
import com.example.helloworld.domain.appdetails.AppDetailsRepository

class AppDetailsRepositorImpl : AppDetailsRepository {
    private val appApi = AppApi()
    private val mapper = AppDetailsMapper()

    override suspend fun get(): AppDetails {
        val dto = appApi.get()
        val domain = mapper.toDomain(dto)
        return domain
    }
}
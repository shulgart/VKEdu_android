package com.example.helloworld.data.appcard

import com.example.helloworld.domain.appcard.AppCardRepository
import com.example.helloworld.domain.appcard.AppCard

class AppCardRepositoryImpl : AppCardRepository {
    private val appCardApi = AppCardApi()

    private val mapper = AppCardMapper()

    override suspend fun get(): List<AppCard> {
        val dto = appCardApi.get()
        val domain = dto.map{ it ->
            mapper.toDomain(it)
        }
        return domain
    }
}
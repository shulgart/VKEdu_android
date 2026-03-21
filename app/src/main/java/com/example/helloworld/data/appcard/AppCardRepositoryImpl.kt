package com.example.helloworld.data.appcard

import com.example.helloworld.domain.appcard.AppCardRepository
import com.example.helloworld.domain.appcard.AppCard
import javax.inject.Inject

class AppCardRepositoryImpl @Inject constructor(
    private val mapper : AppCardMapper
) : AppCardRepository {
    private val appCardApi = AppCardApi()

    override suspend fun get(): List<AppCard> {
        val dto = appCardApi.get()
        val domain = dto.map{ it ->
            mapper.toDomain(it)
        }
        return domain
    }
}
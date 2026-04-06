package com.example.helloworld.data.appcard

import com.example.helloworld.domain.appcard.AppCardRepository
import com.example.helloworld.domain.appcard.AppCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppCardRepositoryImpl @Inject constructor(
    private val api: AppCardApi,
    private val dao: AppCardDao,
    private val mapper : AppCardMapper
) : AppCardRepository {

    override suspend fun get(): Flow<List<AppCard>> {
        return dao.getApps().map { it ->
            if(it.isNotEmpty()) {
                it.map { mapper.toAppCard(it) }
            } else {
                val dto = api.get()
                val domain = dto.map{
                    mapper.toDomain(it)
                }
                val entity = domain.map{
                    mapper.toAppCardEntity(it)
                }
                withContext(Dispatchers.IO) {
                    dao.insertApps(entity)
                }
                domain
            }
        }
    }
}
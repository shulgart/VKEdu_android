package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appdetails.AppDetails
import com.example.helloworld.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): AppDetails {
        val entity = dao.getAppDetails(id)
        if (entity != null) {
            return entityMapper.toDomain(entity)
        } else {
            val dto = appApi.getAppDetails(id)
            val domain = mapper.toDomain(dto)
            val entity = entityMapper.toEntity(domain)
            withContext(Dispatchers.IO) {
                dao.insertAppDetails(entity)
            }
            return domain
        }
    }
}
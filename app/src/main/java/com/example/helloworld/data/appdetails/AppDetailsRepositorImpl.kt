package com.example.helloworld.data.appdetails

import com.example.helloworld.domain.appdetails.AppDetails
import com.example.helloworld.domain.appdetails.AppDetailsRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import javax.inject.Inject

class AppDetailsRepositoryImpl @Inject constructor(
    private val appApi: AppApi,
    private val dao: AppDetailsDao,
    private val mapper: AppDetailsMapper,
    private val entityMapper: AppDetailsEntityMapper,
) : AppDetailsRepository {

    override suspend fun getAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id).map { entity ->
            if (entity != null) {
                entityMapper.toDomain(entity)
            } else {
                val dto = appApi.getAppDetails(id)
                val domain = mapper.toDomain(dto)
                val entity = entityMapper.toEntity(domain)
                withContext(Dispatchers.IO) {
                    dao.insertAppDetails(entity)
                }
                domain
            }
        }
    }

    override fun observeAppDetails(id: String): Flow<AppDetails> {
        return dao.getAppDetails(id)
            .map { it -> entityMapper.toDomain(it!!) }
    }

    override suspend fun toggleWishlist(id: String) {
        val currentEntity = dao.getAppDetails(id).first()
        currentEntity.let {
            dao.updateWishlistStatus(id, !it!!.isInWishlist)
        }
    }
}
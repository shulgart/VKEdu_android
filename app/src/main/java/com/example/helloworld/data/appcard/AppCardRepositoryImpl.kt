package com.example.helloworld.data.appcard

import android.content.Context
import androidx.room.Room
import androidx.room.migration.Migration
import com.example.helloworld.R
import com.example.helloworld.domain.appcard.AppCardRepository
import com.example.helloworld.domain.appcard.AppCard
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.withContext
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Inject
import kotlin.jvm.java

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
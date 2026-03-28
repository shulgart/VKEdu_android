package com.example.helloworld.data.appcard

import com.example.helloworld.R
import com.example.helloworld.data.appcard.dto.AppCardDto
import com.example.helloworld.domain.appcard.AppCategory
import com.example.helloworld.domain.appcard.AppCard
import kotlinx.coroutines.delay
import retrofit2.http.GET
import kotlin.time.Duration.Companion.seconds

interface AppCardApi {
    @GET("/catalog")
    suspend fun get(): List<AppCardDto>
}
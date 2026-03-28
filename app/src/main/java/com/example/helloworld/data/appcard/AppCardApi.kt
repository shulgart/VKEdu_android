package com.example.helloworld.data.appcard

import com.example.helloworld.data.appcard.dto.AppCardDto
import com.example.helloworld.data.appdetails.AppDetailsDto
import retrofit2.http.GET
import retrofit2.http.Path

interface AppCardApi {
    @GET("/catalog")
    suspend fun get(): List<AppCardDto>

    @GET("catalog/{id}")
    suspend fun getAppDetails(@Path("id") id: String): AppDetailsDto
}
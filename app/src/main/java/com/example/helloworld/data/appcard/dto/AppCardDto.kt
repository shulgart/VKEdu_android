package com.example.helloworld.data.appcard.dto

import kotlinx.serialization.Serializable

@Serializable
data class AppCardDto(
    val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)
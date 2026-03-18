package com.example.helloworld.data.appcard

import com.example.helloworld.domain.appcard.AppCategory

data class AppCardDto(
    val name: String,
    val description: String,
    val category: String,
    val icon: Int
)
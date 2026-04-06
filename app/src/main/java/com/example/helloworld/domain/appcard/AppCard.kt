package com.example.helloworld.domain.appcard

data class AppCard(
    val id: String,
    val name: String,
    val description: String,
    val category: AppCategory,
    val iconUrl: String
)
package com.example.helloworld.domain.appcard

data class AppCard(
    val name: String,
    val description: String,
    val category: AppCategory,
    val icon: Int
)
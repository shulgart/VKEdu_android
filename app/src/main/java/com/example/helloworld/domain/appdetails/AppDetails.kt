package com.example.helloworld.domain.appdetails

import com.example.helloworld.domain.appcard.AppCategory

data class AppDetails(
    val id: String,
    val name: String,
    val developer: String,
    val category: AppCategory,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshotUrlList: List<String>?,
    val description: String,
)
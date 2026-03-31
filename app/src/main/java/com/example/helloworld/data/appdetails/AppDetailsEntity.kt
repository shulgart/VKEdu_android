package com.example.helloworld.data.appdetails

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.helloworld.domain.appcard.AppCategory

@Entity(tableName = "app_details")
data class AppDetailsEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val developer: String,
    val category: AppCategory,
    val ageRating: Int,
    val size: Float,
    val iconUrl: String,
    val screenshots: String? = null,
    val description: String,
    val lastUpdated: Long = System.currentTimeMillis(),
    val isInWishlist: Boolean = false
)

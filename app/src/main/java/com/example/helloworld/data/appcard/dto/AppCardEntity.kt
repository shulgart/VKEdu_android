package com.example.helloworld.data.appcard.dto

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class AppCardEntity (
    @PrimaryKey val id: String,
    val name: String,
    val description: String,
    val category: String,
    val iconUrl: String,
)
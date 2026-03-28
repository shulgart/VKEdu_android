package com.example.helloworld.domain.appcard

import androidx.room.TypeConverter
import kotlinx.serialization.SerialName

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: AppCategory): String = category.value

    @TypeConverter
    fun toCategory(value: String): AppCategory = AppCategory.fromString(value)
}
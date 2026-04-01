package com.example.helloworld.data.appdetails

import androidx.room.TypeConverter
import com.example.helloworld.domain.appcard.AppCategory

class CategoryConverter {
    @TypeConverter
    fun fromCategory(category: AppCategory): String = category.value

    @TypeConverter
    fun toCategory(value: String): AppCategory = AppCategory.Companion.fromString(value)
}
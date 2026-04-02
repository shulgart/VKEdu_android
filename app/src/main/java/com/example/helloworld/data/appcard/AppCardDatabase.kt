package com.example.helloworld.data.appcard

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.helloworld.data.appcard.dto.AppCardEntity
import com.example.helloworld.data.appdetails.CategoryConverter

@Database(entities = [AppCardEntity::class], version=1)
@TypeConverters(CategoryConverter::class)
abstract class AppCardDatabase : RoomDatabase() {
    abstract fun appsDao() : AppCardDao
}
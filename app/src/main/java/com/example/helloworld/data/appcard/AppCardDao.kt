package com.example.helloworld.data.appcard

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.helloworld.data.appcard.dto.AppCardEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface AppCardDao {
    @Query("SELECT * FROM AppCardEntity")
    fun getApps(): Flow<List<AppCardEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertApps(apps: List<AppCardEntity>)
}
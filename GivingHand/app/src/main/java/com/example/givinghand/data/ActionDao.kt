package com.example.givinghand.data

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ActionDao {

    @Query("SELECT * FROM actions")
    fun getAllAction(): Flow<List<Action>>

    @Query("SELECT * FROM actions WHERE category_id=:category_id")
    fun getActionsByCategoryId(category_id:Int): Flow<List<Action>>

    @Query("SELECT * FROM actions WHERE id=:id")
    fun getActionById(id:Int): Flow<List<Action>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(action: Action)

    @Update
    suspend fun update(action: Action)

    @Delete
    suspend fun delete(action: Action)
}
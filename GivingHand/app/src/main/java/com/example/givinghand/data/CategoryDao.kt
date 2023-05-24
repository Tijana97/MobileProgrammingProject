package com.example.givinghand.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CategoryDao {
    @Query("SELECT * FROM categories")
    fun getAllCategories(): Flow<List<Category>>

    @Query("SELECT * FROM categories WHERE id=:id")
    fun getCategoryById(id:Int): Flow<List<Category>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(category: Category)
}
package com.example.daggerhilt.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface BlogDao {
@Insert(onConflict = OnConflictStrategy.REPLACE)
suspend fun insert(blogEntity:BlogEntityCache):Long   //long shows which row data was inserted

@Query("SELECT * FROM blogs")
suspend fun getblogs():List<BlogEntityCache>
}
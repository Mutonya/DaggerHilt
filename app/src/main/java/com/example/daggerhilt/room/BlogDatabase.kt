package com.example.daggerhilt.room

import androidx.room.Database
import androidx.room.RoomDatabase
@Database(entities = [BlogEntityCache::class], version = 1)
abstract class BlogDatabase:RoomDatabase() {

    abstract fun blogDao():BlogDao
    companion object{
        val DB_NAME:String = "blog_db"
    }
}
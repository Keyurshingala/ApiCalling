package com.example.vasupractice.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.vasupractice.db.daos.MyTableDao
import com.example.vasupractice.db.tables.MyTable

@Database(
        entities = [MyTable::class],
        version = 1,
        exportSchema = true
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun myTableDao(): MyTableDao
}

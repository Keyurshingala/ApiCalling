package com.example.vasupractice.db.tables

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "MyTable")
data class MyTable(
        val name: String,
        @PrimaryKey val id: Long = System.currentTimeMillis()
)

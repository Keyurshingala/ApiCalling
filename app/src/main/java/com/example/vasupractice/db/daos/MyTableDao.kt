package com.example.vasupractice.db.daos

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.vasupractice.db.tables.MyTable

@Dao
interface MyTableDao {
    @Query("SELECT * FROM MyTable")
    fun getAll(): List<MyTable>

    @Query("SELECT * FROM MyTable")
    fun getAllLive(): LiveData<List<MyTable>>

    @Insert
    fun insert(myTable: MyTable)

    @Update
    fun update(myTable: MyTable)

    @Delete
    fun delete(myTable: MyTable)


//    @Query("SELECT * FROM MyTable WHERE id LIKE :id")
//    suspend fun findById(id: Long): MyTable
//
//    @Insert(onConflict = OnConflictStrategy.REPLACE)
//    suspend fun insertAll(vararg myTable: MyTable)
//
//    @Query("DELETE FROM MyTable")
//    suspend fun clear()
}

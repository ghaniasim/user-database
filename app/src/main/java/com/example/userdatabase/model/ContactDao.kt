package com.example.userdatabase.model

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface ContactDao {
    @Query("SELECT * FROM contactinfo")
    fun getAll(): LiveData<List<ContactInfo>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(contactInfo: ContactInfo)

    @Update
    fun update(contactInfo: ContactInfo)
}
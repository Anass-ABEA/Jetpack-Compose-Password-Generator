package com.whitebatcodes.passwordvault.models.password

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface SavedPasswordDao {

    @Query("Select * FROM password ORDER BY updated_at DESC")
    fun getSavedPasswords(): List<SavedPassword>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(savedPassword : SavedPassword)
}
package com.mufti.bangkit.learn.ilt3.example.data.local.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.mufti.bangkit.learn.ilt3.example.data.local.entity.UserEntity

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUser(userList: List<UserEntity>)

    @Query("SELECT * FROM users")
    fun getAllUser(): List<UserEntity>

    @Query("DELETE FROM users")
    suspend fun deleteAllUser()
}
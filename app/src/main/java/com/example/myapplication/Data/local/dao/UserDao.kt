package com.example.myapplication.Data.local.dao
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.myapplication.Data.model.user

@Dao
interface UserDao {
    @Query("SELECT * FROM users WHERE username = :username")
    suspend fun getUser(username: String): user?

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertUser(user: user)
}
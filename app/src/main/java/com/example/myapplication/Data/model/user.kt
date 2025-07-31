package com.example.myapplication.Data.model
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class user(
    @PrimaryKey val username: String,
    val password: String
)

package com.example.myapplication.Data.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "favourites")
data class favourite(
    @PrimaryKey val user: user,
    val meal: Meal
)

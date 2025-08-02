package com.example.myapplication.Data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "favourites",
    primaryKeys = ["idMeal", "username"],
    foreignKeys = [
        ForeignKey(entity = Meal::class, parentColumns = ["idMeal"], childColumns = ["idMeal"]),
        ForeignKey(entity = user::class, parentColumns = ["username"], childColumns = ["username"])
    ]
)
data class favourite(
    val idMeal: String,
    val username: String
)


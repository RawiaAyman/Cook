package com.example.myapplication.Data.model

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    tableName = "favourites",
    primaryKeys = ["id", "username"],
    foreignKeys = [
        ForeignKey(entity = Meal::class, parentColumns = ["id"], childColumns = ["id"]),
        ForeignKey(entity = user::class, parentColumns = ["username"], childColumns = ["username"])
    ]
)
data class favourite(
    val id: Int,
    val username: String
)


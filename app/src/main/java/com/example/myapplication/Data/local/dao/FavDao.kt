package com.example.myapplication.Data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.Data.model.favourite
import com.example.myapplication.Data.model.Meal

@Dao
interface FavDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<favourite>

    @Query("SELECT * FROM recipes WHERE id = :rId")
    suspend fun getRecipeById(rId: Int): Meal

    @Insert
    suspend fun insertFav(fav: favourite)

    @Delete
    suspend fun deleteFav(fav: favourite)

    @Update
    suspend fun updateFav(fav: favourite)

}
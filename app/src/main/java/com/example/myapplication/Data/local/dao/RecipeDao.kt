package com.example.myapplication.Data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.Data.model.Meal

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Meal>

    @Query("SELECT * FROM recipes WHERE id = :rId")
    suspend fun getRecipeById(rId: Int): Meal

    @Insert
    suspend fun insertRecipe(recipe: Meal)

    @Delete
    suspend fun deleteRecipe(recipe: Meal)

    @Update
    suspend fun updateRecipe(recipe: Meal)

}
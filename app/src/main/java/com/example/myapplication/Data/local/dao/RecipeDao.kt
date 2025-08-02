package com.example.myapplication.Data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.myapplication.Data.model.Meal

@Dao
interface RecipeDao {
    @Query("SELECT * FROM recipes")
    suspend fun getAllRecipes(): List<Meal>

    @Query("SELECT * FROM recipes WHERE idMeal = :rId")
    suspend fun getRecipeById(rId: String): Meal

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertRecipe(recipe: Meal)

    @Delete
    suspend fun deleteRecipe(recipe: Meal)

    @Update
    suspend fun updateRecipe(recipe: Meal)

}
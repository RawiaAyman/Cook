package com.example.myapplication.Data.repo

import com.example.myapplication.Data.local.dao.RecipeDao
import com.example.myapplication.Data.model.Meal

class RecipeRepository(val recipeDao: RecipeDao) {
    suspend fun getAllRecipes(): List<Meal> {
        return recipeDao.getAllRecipes()
    }

    suspend fun getRecipeById(id: String): Meal {
        return recipeDao.getRecipeById(id)
    }

    suspend fun insertRecipe(recipe: Meal) {
        recipeDao.insertRecipe(recipe)
    }

    suspend fun updateRecipe(recipe: Meal) {
        recipeDao.updateRecipe(recipe)
    }

    suspend fun deleteRecipe(recipe: Meal) {
        recipeDao.deleteRecipe(recipe)
    }

}
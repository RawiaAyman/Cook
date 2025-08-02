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
    @Query("""
    SELECT recipes.* FROM recipes
    INNER JOIN favourites ON recipes.idMeal = favourites.idMeal
    WHERE favourites.username = :username
    """)
    suspend fun getAllFavouritesOfUser(username: String): List<Meal>

    @Query("""
    SELECT recipes.* FROM recipes
    INNER JOIN favourites ON recipes.idMeal = favourites.idMeal
    WHERE favourites.username = :username AND recipes.idMeal= :recipeId
    """)
    suspend fun getFavouriteRecipeByUserAndRecipeId(username: String, recipeId: String): Meal?

    @Insert
    suspend fun insertFav(fav: favourite)

    @Delete
    suspend fun deleteFav(fav: favourite)

    @Update
    suspend fun updateFav(fav: favourite)

}
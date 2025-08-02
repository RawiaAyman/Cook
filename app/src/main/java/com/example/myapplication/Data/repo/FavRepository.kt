package com.example.myapplication.Data.repo

import com.example.myapplication.Data.local.dao.FavDao
import com.example.myapplication.Data.model.Meal
import com.example.myapplication.Data.model.favourite

class FavRepository(val favDao: FavDao) {
    suspend fun getALL(username: String): List<Meal> {
        return favDao.getAllFavouritesOfUser(username)
    }

    suspend fun getbyId(username: String, id: String): Meal? {
        return favDao.getFavouriteRecipeByUserAndRecipeId(username, id)
    }

    suspend fun insertFav(fav: favourite) {
        favDao.insertFav(fav)
    }

    suspend fun deleteFav(fav: favourite) {
        favDao.deleteFav(fav)
    }

    suspend fun updateFav(fav: favourite) {
        favDao.updateFav(fav)
    }
}
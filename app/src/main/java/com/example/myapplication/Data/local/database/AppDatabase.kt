package com.example.myapplication.Data.local.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.myapplication.Data.local.dao.FavDao
import com.example.myapplication.Data.local.dao.RecipeDao
import com.example.myapplication.Data.local.dao.UserDao
import com.example.myapplication.Data.model.Meal
import com.example.myapplication.Data.model.favourite
import com.example.myapplication.Data.model.user

@Database(entities = [Meal::class, user::class, favourite::class], version = 5)
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao() : UserDao
    abstract fun recipeDao() : RecipeDao
    abstract fun favDao() : FavDao
    companion object{
        @Volatile
        private var INSTANCE: AppDatabase? = null
        fun getInstance(context: Context): AppDatabase{
            return INSTANCE?: Room.databaseBuilder(
                context.applicationContext,
                AppDatabase::class.java,
                "recipes_database"
            )
                .fallbackToDestructiveMigration(true)
                .build()
                .also { INSTANCE = it }
        }
    }

}

package com.example.myapplication.network

import com.example.myapplication.Data.model.*
import retrofit2.http.GET
import retrofit2.http.Query

interface APIService {

    @GET("search.php")
    suspend fun searchMealsByName(@Query("s") query: String): MealsResponse

    @GET("search.php")
    suspend fun listMealsByFirstLetter(@Query("f") letter: String): MealsResponse

    @GET("lookup.php")
    suspend fun lookupMealDetails(@Query("i") id: String): MealsResponse

    @GET("random.php")
    suspend fun randomMeal(): MealsResponse

    @GET("filter.php")
    suspend fun filterByCategory(@Query("c") category: String): MealsResponse

    @GET("filter.php")
    suspend fun filterByArea(@Query("a") area: String): MealsResponse

    @GET("filter.php")
    suspend fun filterByIngredient(@Query("i") ingredient: String): MealsResponse

    @GET("list.php")
    suspend fun listCategoriesNames(@Query("c") list: String = "list"): CategoryResponse

    @GET("list.php")
    suspend fun listAreasNames(@Query("a") list: String = "list"): AreaResponse

    @GET("list.php")
    suspend fun listIngredientsNames(@Query("i") list: String = "list"): IngredientResponse


}

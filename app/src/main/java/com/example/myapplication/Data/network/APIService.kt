package com.example.myapplication.Data.network


import com.example.myapplication.Data.model.MealsResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface APIService {

    // Search meal by name
    @GET("{version}/{key}/search.php")
    suspend fun searchMealsByName(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("s") query: String
    ): MealsResponse

    // List all meals by first letter
    @GET("{version}/{key}/search.php")
    suspend fun listMealsByFirstLetter(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("f") letter: String
    ): MealsResponse

    // Lookup full meal details by id
    @GET("{version}/{key}/lookup.php")
    suspend fun lookupMealDetails(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("i") id: String
    ): MealsResponse

    @GET("{version}/{key}/random.php")
    suspend fun randomMeal(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1"
    ): MealsResponse

    @GET("{version}/{key}/filter.php")
    suspend fun filterByCategory(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("c") category: String
    ): MealsResponse

    @GET("{version}/{key}/filter.php")
    suspend fun filterByArea(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("a") area: String
    ): MealsResponse

    @GET("{version}/{key}/filter.php")
    suspend fun filterByIngredient(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("i") ingredient: String
    ): MealsResponse

    @GET("{version}/{key}/list.php")
    suspend fun listCategoriesNames(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("c") list: String = "list"
    ): MealsResponse

    @GET("{version}/{key}/list.php")
    suspend fun listAreasNames(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("a") list: String = "list"
    ): MealsResponse

    @GET("{version}/{key}/list.php")
    suspend fun listIngredientsNames(
        @Path("version") version: String = "v1",
        @Path("key") apiKey: String = "1",
        @Query("i") list: String = "list"
    ): MealsResponse
}
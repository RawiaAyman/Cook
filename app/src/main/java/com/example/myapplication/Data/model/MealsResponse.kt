package com.example.myapplication.Data.model

import com.example.myapplication.Data.model.Meal
import com.squareup.moshi.Json

data class MealsResponse(
    @Json(name = "meals") val meals: List<Meal>?
)
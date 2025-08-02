package com.example.myapplication.Data.model

data class IngredientResponse(
    val meals: List<IngredientItem>
)

data class IngredientItem(
    val strIngredient: String
)

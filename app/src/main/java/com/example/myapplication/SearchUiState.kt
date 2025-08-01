package com.example.myapplication

import com.example.myapplication.Data.model.Meal

data class SearchUiState(
    val meals: List<Meal> = emptyList()
)

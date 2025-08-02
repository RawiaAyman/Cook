package com.example.myapplication.ui.search

import com.example.myapplication.Data.model.Meal

data class SearchUiState(
    val meals: List<Meal> = emptyList()
)


package com.example.myapplication.Data.model

data class CategoryResponse(
    val meals: List<CategoryItem>
)

data class CategoryItem(
    val strCategory: String
)

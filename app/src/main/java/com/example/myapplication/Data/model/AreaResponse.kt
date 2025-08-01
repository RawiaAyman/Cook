package com.example.myapplication.Data.model

data class AreaResponse(
    val meals: List<AreaItem>
)

data class AreaItem(
    val strArea: String
)

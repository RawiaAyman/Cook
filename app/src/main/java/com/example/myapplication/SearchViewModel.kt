package com.example.myapplication

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.model.MealsResponse
import com.example.myapplication.Data.network.APIService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class SearchViewModel(private val api: APIService) : ViewModel() {

    private val _uiState = MutableStateFlow(SearchUiState())
    val uiState: StateFlow<SearchUiState> = _uiState

    fun search(type: String, query: String) {
        viewModelScope.launch {
            val safeQuery = query.trim()
            val response = when (type) {
                "Name" -> api.searchMealsByName(query = safeQuery)
                "Category" -> api.filterByCategory(category = safeQuery)
                "Area" -> api.filterByArea(area = safeQuery)
                "Ingredients" -> api.filterByIngredient(ingredient = safeQuery)
                else -> MealsResponse(emptyList())
            }

            _uiState.value = _uiState.value.copy(meals = response.meals ?: emptyList())
        }
    }

    fun loadOptions(type: String, callback: (List<String>) -> Unit) {
        viewModelScope.launch {
            val result = when (type) {
                "Category" -> api.listCategoriesNames().meals
                "Area" -> api.listAreasNames().meals
                "Ingredients" -> api.listIngredientsNames().meals
                else -> emptyList()
            }
            val names = result?.mapNotNull {
                it.strCategory ?: it.strArea ?: it.strMeal
            } ?: emptyList()
            callback(names)
        }
    }
}

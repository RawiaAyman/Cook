package com.example.myapplication.ui.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Data.network.APIService

class SearchViewModelFactory(private val api: APIService) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SearchViewModel::class.java)) {
            return SearchViewModel(api) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}

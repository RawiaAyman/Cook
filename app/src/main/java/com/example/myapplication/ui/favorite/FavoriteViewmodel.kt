package com.example.myapplication.ui.favorite

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapplication.Data.model.Meal
import com.example.myapplication.Data.model.favourite
import com.example.myapplication.Data.repo.FavRepository
import kotlinx.coroutines.launch

class FavoriteViewmodel(val repository: FavRepository): ViewModel() {
    private val _data = MutableLiveData<List<Meal>>()
    private val _recipe = MutableLiveData<Meal>()
    val data : LiveData<List<Meal>>
        get() = _data
    private val recipe : LiveData<Meal>
        get() = _recipe

    fun getdata(username: String){
        viewModelScope.launch {
            _data.value = repository.getALL(username)
        }
    }

    fun getbyId(username: String, id: String){
        viewModelScope.launch {
            _recipe.value = repository.getbyId(username, id)
        }
    }

    fun updateFav(fav: favourite) {
        viewModelScope.launch {
            repository.updateFav(fav)
        }
    }

    fun deleteFav(fav: favourite) {
        viewModelScope.launch {
            repository.deleteFav(fav)
        }
    }

    fun insertFav(fav: favourite) {
        viewModelScope.launch {
            repository.insertFav(fav)
        }
    }
}
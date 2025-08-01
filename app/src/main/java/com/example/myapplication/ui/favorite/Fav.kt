package com.example.myapplication.ui.favorite

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.Data.local.database.AppDatabase
import com.example.myapplication.Data.repo.FavRepository
import com.example.myapplication.databinding.FragmentFavBinding

class Fav : Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: favoriteViewmodel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = FavRepository(AppDatabase.getInstance(requireContext()).favDao())
                return favoriteViewmodel(repository) as T
            }
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
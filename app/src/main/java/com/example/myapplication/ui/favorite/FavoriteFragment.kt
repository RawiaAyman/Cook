package com.example.myapplication.ui.favorite

import android.app.AlertDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.example.myapplication.Data.adapter.FavAdapter
import com.example.myapplication.Data.local.database.AppDatabase
import com.example.myapplication.Data.model.favourite
import com.example.myapplication.Data.repo.FavRepository
import com.example.myapplication.databinding.FragmentFavBinding
import com.example.myapplication.ui.utils.PreferenceHelper

class FavoriteFragment : Fragment() {
    private var _binding: FragmentFavBinding? = null
    private val binding get() = _binding!!
    private val viewmodel: FavoriteViewmodel by viewModels{
        object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                val repository = FavRepository(AppDatabase.getInstance(requireContext()).favDao())
                return FavoriteViewmodel(repository) as T
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
        val rv = binding.favRV
        val adapter = FavAdapter(
            onClick = {
                    it.idMeal.let { id ->
                        val action =
                            FavoriteFragmentDirections.actionFavFragmentToRecipeDetailFragment(id)
                        findNavController().navigate(action)
                    }
            },
            onLongClick = { meal ->
                AlertDialog.Builder(requireContext())
                    .setTitle("Delete Favorite")
                    .setMessage("Are you sure you want to remove '${meal.strMeal}' from your favorites?")
                    .setPositiveButton("Delete") { _, _ ->
                        viewmodel.deleteFav(
                            favourite(meal.idMeal, PreferenceHelper.getUserName(requireContext()))
                        )
                        viewmodel.getdata(PreferenceHelper.getUserName(requireContext()))
                        Toast.makeText(requireContext(), "Deleted from favorites", Toast.LENGTH_SHORT).show()
                    }
                    .setNegativeButton("Cancel", null)
                    .show()
            }

        )
        rv.adapter = adapter
        rv.layoutManager = GridLayoutManager(requireContext(), 2)
        viewmodel.getdata(PreferenceHelper.getUserName(requireContext()))
        viewmodel.data.observe(viewLifecycleOwner){
            if (it.isNullOrEmpty()) {
                adapter.submitList(emptyList())
            } else {
                adapter.submitList(it)
            }
        }

    }

}
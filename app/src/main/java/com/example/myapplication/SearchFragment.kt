package com.example.myapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.core.widget.doAfterTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.Data.network.RetrofitHelper
import com.example.myapplication.Data.adapter.MealAdapter
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var mealAdapter: MealAdapter

    private val searchTypes = listOf("Name", "Category", "Area", "Ingredients")

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        viewModel = ViewModelProvider(
            this,
            SearchViewModelFactory(RetrofitHelper.api)
        )[SearchViewModel::class.java]

        mealAdapter = MealAdapter { meal ->
            meal.idMeal?.let {
                val action = SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(it)
                findNavController().navigate(action)
            }
        }

        binding.mealRecyclerView.adapter = mealAdapter
        setupSearchTypeDropdown()

        binding.searchInput.doAfterTextChanged {
            viewModel.search(binding.searchTypeDropdown.text.toString(), it.toString())
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                mealAdapter.submitList(state.meals)
            }
        }
    }

    private fun setupSearchTypeDropdown() {
        val adapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, searchTypes)
        binding.searchTypeDropdown.setAdapter(adapter)

        binding.searchTypeDropdown.setOnItemClickListener { _, _, _, _ ->
            val type = binding.searchTypeDropdown.text.toString()
            if (type != "Name") {
                viewModel.loadOptions(type) { options ->
                    val optAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_dropdown_item_1line, options)
                    binding.searchInput.setAdapter(optAdapter)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

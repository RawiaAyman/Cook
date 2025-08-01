package com.example.myapplication.ui

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
import com.example.myapplication.R
import com.example.myapplication.databinding.FragmentSearchBinding
import com.example.myapplication.network.RetrofitHelper
import kotlinx.coroutines.launch

class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!
    private lateinit var viewModel: SearchViewModel
    private lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
                val action =
                    SearchFragmentDirections.actionSearchFragmentToRecipeDetailFragment(it)
                findNavController().navigate(action)
            }
        }

        binding.mealRecyclerView.adapter = mealAdapter

        binding.modeChips.setOnCheckedChangeListener { group, checkedId ->
            val type = when (checkedId) {
                R.id.chipName -> "Name"
                R.id.chipCategory -> "Category"
                R.id.chipArea -> "Area"
                R.id.chipIngredient -> "Ingredients"
                else -> "Name"
            }

            if (type != "Name") {
                viewModel.loadOptions(type) { options ->
                    val adapter = ArrayAdapter(
                        requireContext(),
                        android.R.layout.simple_dropdown_item_1line,
                        options
                    )
                    binding.searchInput.setAdapter(adapter)
                    binding.searchInput.showDropDown()
                }
            } else {
                // Clear suggestions
                binding.searchInput.setAdapter(null)
            }
        }

        binding.searchInput.doAfterTextChanged {
            val selectedType = getSelectedSearchType()
            viewModel.search(selectedType, it.toString())
        }

        lifecycleScope.launch {
            viewModel.uiState.collect { state ->
                mealAdapter.submitList(state.meals)
            }
        }
    }

    private fun getSelectedSearchType(): String {
        return when (binding.modeChips.checkedChipId) {
            R.id.chipCategory -> "Category"
            R.id.chipArea -> "Area"
            R.id.chipIngredient -> "Ingredients"
            else -> "Name"
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}

package com.example.myapplication.ui.home

import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.Data.network.RetrofitHelper
import com.example.myapplication.Data.adapter.MealAdapter
import com.example.myapplication.ui.utils.PreferenceHelper
import kotlinx.coroutines.launch

class HomeFragment : Fragment() {

    private lateinit var recyclerView: RecyclerView
    private lateinit var welcomeText: TextView
    private lateinit var mealAdapter: MealAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val view = inflater.inflate(R.layout.fragment_home, container, false)
        recyclerView = view.findViewById(R.id.mealRecyclerView)
        welcomeText = view.findViewById(R.id.welcomeTitle)

        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        // Initialize adapter
        mealAdapter = MealAdapter { meal ->
            val id = meal.idMeal
            if (!id.isNullOrEmpty()) {
                navigateToRecipeDetail(id)
            } else {
                showToast("Invalid meal data")
            }
        }

        recyclerView.adapter = mealAdapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val username = PreferenceHelper.getUserName(requireContext())
        welcomeText.text = "Hello, $username"

        fetchMeals()
    }

    private fun fetchMeals() {
        lifecycleScope.launch {
            val randomLetter = ('a'..'z').random()
            Log.d("HomeFragment", "Fetching meals for letter: $randomLetter")

            try {
                val response = RetrofitHelper.api.listMealsByFirstLetter(
                    version = "v1",
                    apiKey = "1",
                    letter = randomLetter.toString()
                )

                val meals = response.meals
                if (!meals.isNullOrEmpty()) {
                    mealAdapter.submitList(meals)
                } else {
                    Log.w("HomeFragment", "No meals found for '$randomLetter'")
                    showToast("No meals found")
                }

            } catch (e: Exception) {
                Log.e("HomeFragment", "API error: ${e.localizedMessage}", e)
                showToast("Error fetching meals")
            }
        }
    }

    private fun navigateToRecipeDetail(mealId: String) {
        try {
            val bundle = Bundle().apply {
                putString("mealId", mealId)
            }
            findNavController().navigate(R.id.recipeDetailFragment, bundle)
        } catch (e: Exception) {
            Log.e("NAVIGATION_ERROR", "Navigation failed: ${e.localizedMessage}", e)
            showToast("Navigation failed")
        }
    }

    private fun showToast(message: String) {
        Toast.makeText(requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}

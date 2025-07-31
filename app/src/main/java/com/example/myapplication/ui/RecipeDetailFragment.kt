package com.example.myapplication.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.network.RetrofitHelper
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {

    private lateinit var mealImageView: ImageView
    private lateinit var mealNameTextView: TextView
    private lateinit var categoryTextView: TextView
    private lateinit var areaTextView: TextView
    private lateinit var instructionsTextView: TextView

    // ✅ Safe Args
    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_recipe_detail, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        mealImageView = view.findViewById(R.id.mealImageView)
        mealNameTextView = view.findViewById(R.id.mealNameTextView)
        categoryTextView = view.findViewById(R.id.categoryTextView)
        areaTextView = view.findViewById(R.id.areaTextView)
        instructionsTextView = view.findViewById(R.id.instructionsTextView)

        val mealId = args.mealId
        fetchMealDetails(mealId)
    }

    private fun fetchMealDetails(mealId: String) {
        lifecycleScope.launch {
            try {
                val response = RetrofitHelper.api.lookupMealDetails(id = mealId)
                val meal = response.meals?.firstOrNull() ?: return@launch

                mealNameTextView.text = meal.strMeal
                categoryTextView.text = "Category: ${meal.strCategory}"
                areaTextView.text = "Area: ${meal.strArea}"
                instructionsTextView.text = meal.strInstructions

                Glide.with(requireContext())
                    .load(meal.strMealThumb)
                    .into(mealImageView)

            } catch (e: Exception) {
                e.printStackTrace()
                // Optional: show error UI
            }
        }
    }
}

package com.example.myapplication.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebView
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.myapplication.R
import com.example.myapplication.Data.network.RetrofitHelper
import kotlinx.coroutines.launch

class RecipeDetailFragment : Fragment() {

    private lateinit var mealImageView: ImageView
    private lateinit var mealNameTextView: TextView
    private lateinit var categoryTextView: TextView
    private lateinit var areaTextView: TextView
    private lateinit var instructionsTextView: TextView
    private lateinit var toggleInstructionsButton: Button
    private lateinit var watchVideoButton: Button
    private lateinit var favoriteButton: Button
    private lateinit var videoPlayer: WebView
    private lateinit var closeVideoButton: ImageButton

    private var isExpanded = false
    private val args: RecipeDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = inflater.inflate(R.layout.fragment_recipe_detail, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // View references
        mealImageView = view.findViewById(R.id.mealImageView)
        mealNameTextView = view.findViewById(R.id.mealNameTextView)
        categoryTextView = view.findViewById(R.id.categoryTextView)
        areaTextView = view.findViewById(R.id.areaTextView)
        instructionsTextView = view.findViewById(R.id.instructionsTextView)
        toggleInstructionsButton = view.findViewById(R.id.toggleInstructionsButton)
        watchVideoButton = view.findViewById(R.id.watchVideoButton)
        favoriteButton = view.findViewById(R.id.favoriteButton)
        videoPlayer = view.findViewById(R.id.videoPlayer)
        closeVideoButton = view.findViewById(R.id.closeVideoButton)

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
                instructionsTextView.text = meal.strInstructions?.take(100) + "..."

                Glide.with(requireContext())
                    .load(meal.strMealThumb)
                    .into(mealImageView)

                toggleInstructionsButton.setOnClickListener {
                    isExpanded = !isExpanded
                    if (isExpanded) {
                        instructionsTextView.text = meal.strInstructions
                        instructionsTextView.maxLines = Int.MAX_VALUE
                        toggleInstructionsButton.text = "Show Less"
                    } else {
                        instructionsTextView.text = meal.strInstructions?.take(100) + "..."
                        instructionsTextView.maxLines = 4
                        toggleInstructionsButton.text = "Show More"
                    }
                }

                watchVideoButton.setOnClickListener {
                    val videoId = meal.strYoutube?.substringAfter("v=")
                    if (!videoId.isNullOrEmpty()) {
                        videoPlayer.settings.javaScriptEnabled = true
                        videoPlayer.loadUrl("https://www.youtube.com/embed/$videoId?autoplay=1")
                        videoPlayer.visibility = View.VISIBLE
                        closeVideoButton.visibility = View.VISIBLE
                    }
                }

                closeVideoButton.setOnClickListener {
                    videoPlayer.loadUrl("about:blank")
                    videoPlayer.visibility = View.GONE
                    closeVideoButton.visibility = View.GONE
                }

                // favoriteButton logic → left for your teammate

            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}

package com.example.myapplication.Data.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Data.model.Meal
import com.example.myapplication.R

class MealAdapter(private val onClick: (Meal) -> Unit) :
    ListAdapter<Meal, MealAdapter.MealViewHolder>(MealDiffCallback()) {

    inner class MealViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val mealImage: ImageView = itemView.findViewById(R.id.mealImage)
        private val mealName: TextView = itemView.findViewById(R.id.mealName)

        fun bind(meal: Meal) {
            mealName.text = meal.strMeal
            Glide.with(itemView.context).load(meal.strMealThumb).into(mealImage)
            itemView.setOnClickListener { onClick(meal) }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MealViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_meal, parent, false)
        return MealViewHolder(view)
    }

    override fun onBindViewHolder(holder: MealViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class MealDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem
}

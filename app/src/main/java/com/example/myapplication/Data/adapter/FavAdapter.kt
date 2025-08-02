package com.example.myapplication.Data.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myapplication.Data.model.Meal
import com.example.myapplication.databinding.FavListItemBinding

class FavAdapter(private val onClick: (Meal) -> Unit) :
    ListAdapter<Meal, FavAdapter.FavViewHolder>(FavDiffCallback()) {

    inner class FavViewHolder(private val binding: FavListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(meal: Meal) {
            binding.title.text = meal.strMeal
            Glide.with(binding.root.context)
                .load(meal.strMealThumb)
                .into(binding.image)

            binding.root.setOnClickListener {
                onClick(meal)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavViewHolder {
        val binding = FavListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FavViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class FavDiffCallback : DiffUtil.ItemCallback<Meal>() {
    override fun areItemsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem.idMeal == newItem.idMeal

    override fun areContentsTheSame(oldItem: Meal, newItem: Meal): Boolean =
        oldItem == newItem
}

package com.app.recipeapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.recipeapp.databinding.RecyclerviewItemVerticalBinding
import com.app.recipeapp.pojo.network.Hit
import com.app.recipeapp.pojo.network.Recipe

class RecipeAdapter : RecyclerView.Adapter<RecipeAdapter.RecipeViewHolder>() {

    private var hits: List<Hit> = emptyList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecipeViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding: RecyclerviewItemVerticalBinding =
            RecyclerviewItemVerticalBinding.inflate(inflater, parent, false)
        return RecipeViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RecipeViewHolder, position: Int) {
        val recipe = hits[position].recipe

        if (recipe != null) {
            holder.bind(recipe)
        }


    }

    override fun getItemCount(): Int = hits.size

    fun updateData(hitsList: List<Hit>?) {
        if (hitsList != null) {
            hits = hitsList
            notifyDataSetChanged()
        }
    }

    inner class RecipeViewHolder(private val binding: RecyclerviewItemVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipe = recipe

            binding.executePendingBindings()
        }
    }
}
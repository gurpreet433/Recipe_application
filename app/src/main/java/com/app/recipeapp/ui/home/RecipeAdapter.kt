package com.app.recipeapp.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.app.recipeapp.R
import com.app.recipeapp.databinding.RecyclerviewItemVerticalBinding
import com.app.recipeapp.pojo.network.Hit
import com.app.recipeapp.pojo.network.Recipe
import com.bumptech.glide.Glide

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
        holder.bind(recipe)
    }

    override fun getItemCount(): Int = hits.size

    fun updateData(newHitsList: List<Hit>?) {
        val diffResult = DiffUtil.calculateDiff(HitDiffCallback(hits, newHitsList))
        hits = newHitsList.orEmpty()
        diffResult.dispatchUpdatesTo(this)
    }

    inner class RecipeViewHolder(private val binding: RecyclerviewItemVerticalBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(recipe: Recipe) {
            binding.recipe = recipe
            binding.executePendingBindings()

            Glide.with(binding.root.context)
                .load(binding.recipe?.image)
                .into(binding.imageBackground)
        }
    }

    class HitDiffCallback(private val oldList: List<Hit>, private val newList: List<Hit>?) : DiffUtil.Callback() {
        override fun getOldListSize(): Int = oldList.size
        override fun getNewListSize(): Int = newList?.size ?: 0

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList?.get(newItemPosition)
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldList[oldItemPosition] == newList?.get(newItemPosition)
        }
    }
}
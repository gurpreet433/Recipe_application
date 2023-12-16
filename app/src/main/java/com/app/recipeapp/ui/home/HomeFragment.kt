package com.app.recipeapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recipeapp.R
import com.app.recipeapp.databinding.FragmentHomeBinding
import com.app.recipeapp.pojo.network.Recipe
import com.app.recipeapp.pojo.network.RecipeState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), RecipeAdapter.OnRecipeItemClickListener {

    private val TAG = "RecipeViewModel"

    private var binding : FragmentHomeBinding? = null
    private val recipeViewModel: HomeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter(this)
        binding?.verticalRecyclerview?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)


        recipeViewModel.recipeState.observe(viewLifecycleOwner) { state ->
            binding?.progressBar?.visibility = if (state is RecipeState.Loading) View.VISIBLE else View.GONE
            when (state) {
                is RecipeState.Loading -> {
                    Log.d(TAG, "Received loading")
                }

                is RecipeState.Success -> {
                    val recipeSearchResponse = state.recipeSearchResponse
                    recipeAdapter.updateData(recipeSearchResponse.hits)
                }

                is RecipeState.Error -> {
                    val errorMessage = state.errorMessage
                    Log.d(TAG, "Received error $state ${state.errorMessage}")
                }
            }
        }

        return binding!!.root
    }

    override fun onRecipeItemClick(recipe: Recipe) {
        // Handle the click event here
        val recipeName = recipe.label
        Toast.makeText(requireContext(), "Clicked on $recipeName", Toast.LENGTH_SHORT).show()

        // If you need to perform any other action on item click, you can do it here
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
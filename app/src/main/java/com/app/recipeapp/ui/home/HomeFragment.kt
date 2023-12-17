package com.app.recipeapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.recipeapp.R
import com.app.recipeapp.databinding.FragmentHomeBinding
import com.app.recipeapp.pojo.network.Recipe
import com.app.recipeapp.pojo.network.RecipeState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment(), RecipeAdapter.OnRecipeItemClickListener,
    RecipeHorizontalAdapter.OnRecipeItemHorizontalClickListener {

    private val TAG = "RecipeViewModel"

    private var binding: FragmentHomeBinding? = null
    private val recipeViewModel: HomeViewModel by viewModels()
    private lateinit var recipeAdapter: RecipeAdapter
    private lateinit var recipeHorizontalAdapter: RecipeHorizontalAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recipeAdapter = RecipeAdapter(this)
        recipeHorizontalAdapter = RecipeHorizontalAdapter(this)

        binding?.verticalRecyclerview?.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = recipeAdapter
        }

        binding?.horizontalRecyclerview?.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            adapter = recipeHorizontalAdapter
        }

        binding?.calendar?.setOnClickListener {
            Toast.makeText(requireContext(),
                requireContext().getString(R.string.calendar_clicked_todo), Toast.LENGTH_SHORT).show()

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
            binding?.group?.visibility = if (state is RecipeState.Loading) View.GONE else View.VISIBLE
            when (state) {
                is RecipeState.Loading -> {
                    Log.d(TAG, "Received loading")
                }

                is RecipeState.Success -> {
                    val recipeSearchResponse = state.recipeSearchResponse
                    recipeAdapter.updateData(recipeSearchResponse.hits?.subList(0, 5)) // just for demo purposes showing 4, api min is 20
                    recipeHorizontalAdapter.updateData(recipeSearchResponse.hits?.subList(6, recipeSearchResponse.hits.size - 1))
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
        val recipeName = recipe.label
        //Toast.makeText(requireContext(), "Clicked on $recipeName", Toast.LENGTH_SHORT).show()

        val navController = findNavController()
        val bundle = bundleOf("recipe" to recipe)
        navController.navigate(R.id.action_homeFragment_to_detailsFragment, bundle)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}
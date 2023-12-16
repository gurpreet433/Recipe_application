package com.app.recipeapp.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import com.app.recipeapp.R
import com.app.recipeapp.databinding.FragmentHomeBinding
import com.app.recipeapp.pojo.network.RecipeState
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val TAG = "RecipeViewModel"

    private var binding : FragmentHomeBinding? = null
    private val recipeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil
            .inflate<FragmentHomeBinding>(inflater, R.layout.fragment_home, container, false)

        // Observe LiveData
        recipeViewModel.recipeState.observe(viewLifecycleOwner) { state ->
            binding?.progressBar?.visibility = if (state is RecipeState.Loading) View.VISIBLE else View.GONE
            when (state) {
                is RecipeState.Loading -> {
                    Log.d(TAG, "Received loading")
                }

                is RecipeState.Success -> {
                    // Update UI with recipeSearchResponse
                    val recipeSearchResponse = state.recipeSearchResponse
                    // Handle success UI state
                    Log.d(TAG, "Received response: $recipeSearchResponse")
                }

                is RecipeState.Error -> {
                    // Show error message or handle error UI state
                    val errorMessage = state.errorMessage
                    // Handle error UI state
                    Log.d(TAG, "Received error $state ${state.errorMessage}")
                }
            }
        }

        return binding!!.root
    }

}
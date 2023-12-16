package com.app.recipeapp.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.app.recipeapp.pojo.network.RecipeState
import com.app.recipeapp.repository.RecipeRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class RecipeViewModel @Inject constructor(
    private val repository: RecipeRepository
) : ViewModel() {
    private val _recipeState = MutableLiveData<RecipeState>()
    val recipeState: LiveData<RecipeState> get() = _recipeState

    init {
        searchRecipes("food")
    }

    private fun searchRecipes(query: String) {
        viewModelScope.launch {
            _recipeState.value = RecipeState.Loading
            try {
                val response = repository.searchRecipes(query)
                _recipeState.value = RecipeState.Success(response)
            } catch (e: Exception) {
                _recipeState.value = RecipeState.Error(e.message ?: "Unknown error")
            }
        }
    }
}
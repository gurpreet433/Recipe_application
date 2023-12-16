package com.app.recipeapp.pojo.network

sealed class RecipeState {
    object Loading : RecipeState()
    data class Success(val recipeSearchResponse: RecipeSearchResponse) : RecipeState()
    data class Error(val errorMessage: String) : RecipeState()
}

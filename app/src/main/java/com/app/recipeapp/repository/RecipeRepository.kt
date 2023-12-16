package com.app.recipeapp.repository

import com.app.recipeapp.api.RecipeApiService
import com.app.recipeapp.pojo.network.RecipeSearchResponse

class RecipeRepository(private val apiService: RecipeApiService) {

    suspend fun searchRecipes(query: String): RecipeSearchResponse {
        return apiService.searchRecipes(query)
    }
}
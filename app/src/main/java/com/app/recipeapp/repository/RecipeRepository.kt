package com.app.recipeapp.repository

import com.app.recipeapp.api.ApiConstants
import com.app.recipeapp.api.RecipeApiService
import com.app.recipeapp.pojo.network.RecipeSearchResponse

class RecipeRepository(private val apiService: RecipeApiService) {

    suspend fun searchRecipes(query: String): RecipeSearchResponse {

        val appId = ApiConstants.API_APP_ID
        val appKey = ApiConstants.API_APP_KEY

        return apiService.searchRecipes(appId, appKey, query)
    }
}
package com.app.recipeapp.api

import com.app.recipeapp.pojo.network.RecipeSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {

    @GET("recipes/v2")
    suspend fun searchRecipes(
        @Query("type") type: String,
        @Query("app_id") appId: String,
        @Query("app_key") appKey: String,
        @Query("q") query: String
    ): RecipeSearchResponse
}

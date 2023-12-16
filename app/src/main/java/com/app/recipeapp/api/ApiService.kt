package com.app.recipeapp.api

import com.app.recipeapp.pojo.network.RecipeSearchResponse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipeApiService {
    @GET("search")
    suspend fun searchRecipes(@Query("q") query: String): RecipeSearchResponse
}

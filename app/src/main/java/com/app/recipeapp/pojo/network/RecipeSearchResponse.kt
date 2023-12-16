package com.app.recipeapp.pojo.network

data class RecipeSearchResponse(
    val from: Int,
    val to: Int,
    val count: Int,
    val hits: List<Hit>?
)
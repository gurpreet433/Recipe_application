package com.app.recipeapp.pojo.network

data class Recipe(
    val uri: String?,
    val label: String?,
    val image: String?,
    val images: Images?,
    val source: String?,
    val url: String?,
    val shareAs: String?,
    val yield: Int?,
    val dietLabels: List<String>?,
    val healthLabels: List<String>?,
    val cautions: List<String>?,
    val ingredientLines: List<String>?,
    val ingredients: List<Ingredient>?
)
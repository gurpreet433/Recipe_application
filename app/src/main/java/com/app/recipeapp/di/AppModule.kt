package com.app.recipeapp.di

import com.app.recipeapp.api.ApiConstants.API_BASE_URL
import com.app.recipeapp.api.RecipeApiService
import com.app.recipeapp.repository.RecipeRepository
import com.app.recipeapp.ui.home.HomeViewModel
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {


    @Provides
    @Singleton
    fun provideRecipeApiService(): RecipeApiService {
        return Retrofit.Builder()
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(RecipeApiService::class.java)
    }

    @Provides
    @Singleton
    fun provideRecipeRepository(apiService: RecipeApiService): RecipeRepository {
        return RecipeRepository(apiService)
    }

    @Provides
    @Singleton
    fun provideRecipeViewModel(repository: RecipeRepository): HomeViewModel {
        return HomeViewModel(repository)
    }
}

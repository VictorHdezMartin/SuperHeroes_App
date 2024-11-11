package com.example.superheroes.Utils

import com.example.superheroes.Data.retroFitToken
import com.example.superheroes.Services.SuperHeroesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitProvider {
    companion object {
        fun getRetroFit(): SuperHeroesService {
            val retroFit = Retrofit.Builder()
                .baseUrl("https://superheroeapi.com/api/${retroFitToken}/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retroFit.create(SuperHeroesService::class.java)
        }
    }
}
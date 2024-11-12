package com.example.superheroes.utils

import com.example.superheroes.data.retroFitToken
import com.example.superheroes.services.SuperHeroesService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class RetroFitProvider {
    companion object {
        fun getRetroFit(): SuperHeroesService {
            val retroFit = Retrofit.Builder()
                .baseUrl("https://superheroapi.com/api/${retroFitToken}/")
                .addConverterFactory(GsonConverterFactory.create())
                .build()

            return retroFit.create(SuperHeroesService::class.java)
        }
    }
}
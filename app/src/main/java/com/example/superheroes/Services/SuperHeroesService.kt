package com.example.superheroes.Services

import com.example.superheroes.Data.SuperHero
import com.example.superheroes.Data.SuperHeroesResponse
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroesService {
    @GET("search/{name}")
    suspend fun findSuperHeroesByName (@Path("name")query: String): SuperHeroesResponse

    @GET("{character-id}")
    suspend fun findSuperHeroesById(@Path("character-id") id: String): SuperHero


}
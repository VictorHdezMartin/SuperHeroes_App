package com.example.superheroes.services

import com.example.superheroes.data.SuperHeroesResponse
import com.example.superheroes.data.FichaSuperHeroe
import retrofit2.http.GET
import retrofit2.http.Path

interface SuperHeroesService {
    @GET("search/{name}")
    suspend fun findSuperHeroesByName (@Path("name")query: String): SuperHeroesResponse

    //@GET("{character-id}")
    //suspend fun findSuperHeroesById(@Path("character-id") id: String): SuperHero

    @GET("{character-id}")
    suspend fun findSuperHeroesById(@Path("character-id") id: String): FichaSuperHeroe


}
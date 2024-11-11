package com.example.superheroes.Data

import com.google.gson.annotations.SerializedName

data class SuperHeroesResponse(
    @SerializedName("response")    val response: String,
    @SerializedName("results-for") val resultFor: String,
    @SerializedName("results")     val results: List<SuperHero> ){

    }
    data class SuperHero (
        @SerializedName("id")      val id: String,
        @SerializedName("name")    val name: String  ){

    }

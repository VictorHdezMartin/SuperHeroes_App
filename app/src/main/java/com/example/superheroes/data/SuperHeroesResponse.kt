package com.example.superheroes.data

import com.google.gson.annotations.SerializedName

data class SuperHeroesListResponse(
    @SerializedName("response")    val response: String,
    @SerializedName("results-for") val resultFor: String,
    @SerializedName("results")     val results: List<SuperHeroeClass>,
    ){ }

data class SuperHeroeClass (
    @SerializedName("id")      val id: String,
    @SerializedName("name")    val name: String,
    @SerializedName ("image")  val image: Image,
    ) { }

data class Image (
    @SerializedName ("url") val url: String,
    ) { }




package com.victor.balboa.poketinder_2023.data.network

import com.victor.balboa.poketinder_2023.data.model.PokemonListResponse
import retrofit2.Response
import retrofit2.http.GET

interface PokemonApi {
    @GET("?limit=40")
    suspend fun getPokemons(): Response<PokemonListResponse>
}
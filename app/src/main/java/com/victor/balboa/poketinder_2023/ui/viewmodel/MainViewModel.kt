package com.victor.balboa.poketinder_2023.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.balboa.poketinder_2023.data.model.PokemonResponse
import com.victor.balboa.poketinder_2023.data.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainViewModel: ViewModel() {

    //To see if works
    private val retrofit: Retrofit = getRetrofit()

    val isLoading = MutableLiveData<Boolean>()
    val pokemonList = MutableLiveData<List<PokemonResponse>>()
    val errorPokeApi = MutableLiveData<Boolean>()

    init {
        getAllPokemons()
    }

    private fun getAllPokemons() {
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
            isLoading.postValue(false)
            if (call.isSuccessful) {
                call.body()?.let { body ->
                    pokemonList.postValue(body.results)
                }
            } else {
                errorPokeApi.postValue(true)
            }
        }
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

}




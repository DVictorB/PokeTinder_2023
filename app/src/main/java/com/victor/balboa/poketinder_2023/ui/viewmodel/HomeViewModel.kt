package com.victor.balboa.poketinder_2023.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.victor.balboa.poketinder_2023.data.database.PokemonDatabase
import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity
import com.victor.balboa.poketinder_2023.data.model.PokemonResponse
import com.victor.balboa.poketinder_2023.data.network.PokemonApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class HomeViewModel: ViewModel() {

    val isLoading = MutableLiveData<Boolean>()
    val pokemonList = MutableLiveData<List<PokemonResponse>>()
    val errorPokeApi = MutableLiveData<Boolean>()

    private val POKEMON_DATABASE_NAME = "pokemon_database"

    init {
        getAllPokemons()
    }

    private fun getAllPokemons(){
        isLoading.postValue(true)
        CoroutineScope(Dispatchers.IO).launch {
            isLoading.postValue(false)
            val call = getRetrofit().create(PokemonApi::class.java).getPokemons()
            if (call.isSuccessful){
                call.body()?.let{
                    pokemonList.postValue(it.results)
                }
            }
        }
    }

    fun savePokemon(pokemonResponse: PokemonResponse, context: Context) {
        val myPokemon = MyPokemonEntity(
            name = pokemonResponse.name,
            image = pokemonResponse.getPokemonImage(),
            idPokemon = pokemonResponse.getPokemonId()
        )
        viewModelScope.launch {
            getRoomDatabase(context).getPokemonDao().insert(myPokemon)
        }
    }

    private fun getRoomDatabase(context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            POKEMON_DATABASE_NAME
        ).build()
    }

    private fun getRetrofit() : Retrofit {
        return Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/pokemon/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}
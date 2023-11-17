package com.victor.balboa.poketinder_2023.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.room.Room
import com.victor.balboa.poketinder_2023.data.database.PokemonDatabase
import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class FavoriteViewModel(): ViewModel() {

    private val POKEMON_DATA_BASE_NAME = "pokemon_database"

    val myPokemonList = MutableLiveData<List<MyPokemonEntity>>()

    fun getPokemons(context: Context) {
        CoroutineScope(Dispatchers.IO).launch {
            val myPokemons = getRoomDataBase(context).getPokemonDao().getAllPokemons()
            myPokemonList.postValue(myPokemons)
        }
    }

    fun deleteAllPokemon(context: Context) {
        viewModelScope.launch {
            getRoomDataBase(context).getPokemonDao().deleteTable()
            myPokemonList.postValue(emptyList())
        }
    }

    private fun getRoomDataBase(context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            POKEMON_DATA_BASE_NAME).build()
    }

}

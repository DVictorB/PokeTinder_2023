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
import kotlinx.coroutines.withContext

class FavoriteViewModel(): ViewModel(){

    private val POKEMON_DATABASE_NAME = "pokemon_database"

    val myPokemonList = MutableLiveData<List<MyPokemonEntity>>()

    private var isDataLoaded = false

    fun getMyPokemons(context: Context) {
        if (!isDataLoaded) {
            viewModelScope.launch {
                val myPokemons = withContext(Dispatchers.IO) {
                    getRoomDataBase(context).getPokemonDao().getAllPokemons()
                }
                // Filtra los duplicados antes de asignar a myPokemonList
                val uniquePokemons = myPokemons.distinctBy { it.idPokemon }
                myPokemonList.postValue(uniquePokemons)
            }
            isDataLoaded = true
        }
    }

    // Método para cargar los Pokémon después de realizar operaciones de inserción o eliminación
    suspend fun loadPokemons(context: Context) {
        val myPokemons = withContext(Dispatchers.IO) {
            getRoomDataBase(context).getPokemonDao().getAllPokemons()
        }
        // Filtra los duplicados antes de asignar a myPokemonList
        val uniquePokemons = myPokemons.distinctBy { it.idPokemon }

        myPokemonList.postValue(uniquePokemons)
    }

    suspend fun addPokemon(context: Context, pokemon: MyPokemonEntity) {
        withContext(Dispatchers.IO) {
            getRoomDataBase(context).getPokemonDao().insertAndUpdateList(pokemon)
        }
        // Después de agregar, carga los Pokémon nuevamente
        loadPokemons(context)
    }

    suspend fun deleteAllPokemon(context: Context) {
        withContext(Dispatchers.IO) {
            getRoomDataBase(context).getPokemonDao().deleteTable()
        }
        // Después de eliminar, carga los Pokémon nuevamente
        loadPokemons(context)
    }

    private fun getRoomDataBase(context: Context): PokemonDatabase {
        return Room.databaseBuilder(
            context,
            PokemonDatabase::class.java,
            POKEMON_DATABASE_NAME).build()
    }
}
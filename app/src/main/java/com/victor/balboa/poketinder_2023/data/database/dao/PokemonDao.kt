package com.victor.balboa.poketinder_2023.data.database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity

@Dao
interface PokemonDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertIfNotExists(pokemon: MyPokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(pokemon: MyPokemonEntity)

    // Después de insertar, actualiza la lista
    @Transaction
    suspend fun insertAndUpdateList(pokemon: MyPokemonEntity) {
        insert(pokemon)
        // Recarga la lista después de la inserción
        updateList()
    }


    @Query("SELECT * FROM pokemon_table")
    suspend fun getAllPokemons() : List<MyPokemonEntity>

    // Método para actualizar la lista
    @Query("SELECT * FROM pokemon_table")
    suspend fun updateList(): List<MyPokemonEntity>

    @Query("DELETE FROM pokemon_table")
    suspend fun deleteTable()
}
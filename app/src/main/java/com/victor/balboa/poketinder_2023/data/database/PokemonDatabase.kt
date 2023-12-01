package com.victor.balboa.poketinder_2023.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.victor.balboa.poketinder_2023.data.database.dao.PokemonDao
import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity

@Database(entities = [MyPokemonEntity::class], version = 1, exportSchema = false)
//Add exportSchema by chat
abstract class PokemonDatabase : RoomDatabase() {

    abstract fun getPokemonDao() : PokemonDao

}
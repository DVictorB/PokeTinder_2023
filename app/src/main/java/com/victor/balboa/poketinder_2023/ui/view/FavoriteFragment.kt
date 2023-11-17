package com.victor.balboa.poketinder_2023.ui.view

import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity
import com.victor.balboa.poketinder_2023.databinding.FragmentFavoriteBinding
import com.victor.balboa.poketinder_2023.ui.viewmodel.FavoriteViewModel

class FavoriteFragment: BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private var listMyPokemon = mutableListOf<MyPokemonEntity>()

    private val adapter by lazy { MyPokemonsAdapter(listMyPokemon) }

    override fun onCreate(saved)

}
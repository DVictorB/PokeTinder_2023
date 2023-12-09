package com.victor.balboa.poketinder_2023.ui.view

import android.os.Bundle
import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity
import com.victor.balboa.poketinder_2023.databinding.FragmentFavoriteBinding
import com.victor.balboa.poketinder_2023.ui.adapter.MyPokemonsAdapter
import com.victor.balboa.poketinder_2023.ui.viewmodel.FavoriteViewModel
import kotlinx.coroutines.launch

class FavoriteFragment : BaseFragment<FragmentFavoriteBinding>(FragmentFavoriteBinding::inflate) {

    private val favoriteViewModel: FavoriteViewModel by viewModels()

    private val listMyPokemon = mutableListOf<MyPokemonEntity>()

    private val adapter by lazy { MyPokemonsAdapter(listMyPokemon) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        favoriteViewModel.getMyPokemons(requireContext())

        binding.rvPokemons.adapter = adapter

        favoriteViewModel.myPokemonList.observe(this) { myPokemons ->
            // Filtra los duplicados antes de agregar a listMyPokemon
            val uniquePokemons = myPokemons.distinctBy { it.idPokemon }
            listMyPokemon.clear()
            listMyPokemon.addAll(uniquePokemons)
            adapter.notifyDataSetChanged()
            // Mostrar u ocultar el TextView según la lista esté vacía o no
            binding.tvEmptyList.visibility = if (listMyPokemon.isEmpty()) View.VISIBLE else View.GONE
        }

        binding.floatingActionDelete.setOnClickListener {
            lifecycleScope.launch {
                favoriteViewModel.deleteAllPokemon(requireContext())
            }
        }
    }

}
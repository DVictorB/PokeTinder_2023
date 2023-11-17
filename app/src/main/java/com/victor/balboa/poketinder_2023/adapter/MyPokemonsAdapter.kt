package com.victor.balboa.poketinder_2023.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.victor.balboa.poketinder_2023.data.database.entities.MyPokemonEntity
import com.victor.balboa.poketinder_2023.databinding.ItemPokemonSavedBinding
import com.victor.balboa.poketinder_2023.holder.MyPokemonsHolder

class MyPokemonsAdapter(val list: List<MyPokemonEntity>):
    RecyclerView.Adapter<MyPokemonsHolder>() {

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyPokemonsHolder {
            val view = ItemPokemonSavedBinding.inflate(LayoutInflater.from((parent.context)), parent,false)
            return MyPokemonsHolder(view.root)
        }

        override fun onBindViewHolder(holder: MyPokemonsHolder, position: Int) {
            val item = list[position]
            holder.bind(item)
        }

        override fun getItemCount(): Int = list.size

    }

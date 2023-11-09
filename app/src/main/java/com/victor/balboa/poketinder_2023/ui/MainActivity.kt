package com.victor.balboa.poketinder_2023.ui

import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible

import androidx.recyclerview.widget.DefaultItemAnimator

import com.victor.balboa.poketinder_2023.adapter.PokemonAdapter
import com.victor.balboa.poketinder_2023.databinding.ActivityMainBinding
import com.victor.balboa.poketinder_2023.data.model.PokemonResponse
import com.victor.balboa.poketinder_2023.data.network.PokemonApi
import com.victor.balboa.poketinder_2023.ui.viewmodel.MainViewModel

import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeableMethod

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate),
    CardStackListener,
    PokemonAdapter.Callback {

    private val adapter by lazy { PokemonAdapter(listPokemon, this) }
    private val listPokemon:List<PokemonResponse> = emptyList()
    private val manager by lazy { CardStackLayoutManager(this, this) }
    //Lab08
    private val viewModel by lazy { MainViewModel() }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        initializeTinderCard()

        //Lab08
        viewModel.pokemonList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()
        }

        viewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }

        viewModel.errorPokeApi.observe(this) {
            Toast.makeText(this, "Error de Api", Toast.LENGTH_SHORT).show()//Complete
        }
    }


    private fun initializeTinderCard(){
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollVertical(true)
        manager.setCanScrollHorizontal(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderPokemon.layoutManager = manager

        binding.rvTinderPokemon.adapter = adapter

        binding.rvTinderPokemon.itemAnimator.apply {
            if(this is DefaultItemAnimator){
                supportsChangeAnimations = false
            }
        }
    }

    override fun onClickPokemonInformation(pokemon: PokemonResponse) {
        Toast.makeText(this, "Click Pokemon: ${pokemon.name}", Toast.LENGTH_SHORT).show()
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {
        if (direction != null) {
            Log.e("OnCardSwiped", direction.name)
        }
    }

    override fun onCardRewound() {
    }

    override fun onCardCanceled() {
    }

    override fun onCardAppeared(view: View?, position: Int) {
    }

    override fun onCardDisappeared(view: View?, position: Int) {
    }
}

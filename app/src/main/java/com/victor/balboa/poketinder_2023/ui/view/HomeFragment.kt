package com.victor.balboa.poketinder_2023.ui.view

import android.os.Bundle
import android.view.View
import android.view.animation.DecelerateInterpolator
import android.view.animation.LinearInterpolator
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DefaultItemAnimator
import com.victor.balboa.poketinder_2023.adapter.PokemonAdapter
import com.victor.balboa.poketinder_2023.data.model.PokemonResponse
import com.victor.balboa.poketinder_2023.databinding.FragmentHomeBinding
import com.victor.balboa.poketinder_2023.ui.viewmodel.HomeViewModel
import com.yuyakaido.android.cardstackview.CardStackLayoutManager
import com.yuyakaido.android.cardstackview.CardStackListener
import com.yuyakaido.android.cardstackview.Direction
import com.yuyakaido.android.cardstackview.Duration
import com.yuyakaido.android.cardstackview.RewindAnimationSetting
import com.yuyakaido.android.cardstackview.StackFrom
import com.yuyakaido.android.cardstackview.SwipeAnimationSetting
import com.yuyakaido.android.cardstackview.SwipeableMethod

class HomeFragment: BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::inflate),
    CardStackListener, PokemonAdapter.Callback {

    private val adapter by lazy { PokemonAdapter(listPokemon, this) }

    private var listPokemon: List<PokemonResponse> = emptyList()

    private val manager by lazy { CardStackLayoutManager(context, this) }

    private val homeViewModel by lazy { HomeViewModel() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        observeValues()
        initializeTinderCard()
    }

    //button to listen to the swipe
    private fun observeValues() {
        binding.floatingActionButton.setOnClickListener {
            // Rewind
            val setting = RewindAnimationSetting.Builder()
                .setDirection(Direction.Bottom)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setRewindAnimationSetting(setting)
            binding.rvTinderPokemon.rewind()
        }

        binding.floatingActionButton2.setOnClickListener {
            //Skip
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderPokemon.swipe()
        }

        binding.floatingActionButton3.setOnClickListener {
            //Like
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(DecelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.rvTinderPokemon.swipe()
        }

        homeViewModel.isLoading.observe(this) {
            binding.progressBar.isVisible = it
        }
        //pass list update
        homeViewModel.pokemonList.observe(this) {
            adapter.list = it
            adapter.notifyDataSetChanged()
            //then make the buttons visible
            binding.floatingActionButton.visibility = View.VISIBLE
            binding.floatingActionButton2.visibility = View.VISIBLE
            binding.floatingActionButton3.visibility = View.VISIBLE
        }
    }

    private fun initializeTinderCard() {
        manager.setStackFrom(StackFrom.None)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(8.0f)
        manager.setScaleInterval(0.95f)
        manager.setSwipeThreshold(0.3f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.HORIZONTAL)
        manager.setCanScrollHorizontal(true)
        manager.setCanScrollVertical(true)
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setOverlayInterpolator(LinearInterpolator())

        binding.rvTinderPokemon.layoutManager = manager

        binding.rvTinderPokemon.adapter = adapter

        binding.rvTinderPokemon.itemAnimator.apply {
            if (this is DefaultItemAnimator) {
                supportsChangeAnimations = false
            }
        }
    }

    override fun onCardDragging(direction: Direction?, ratio: Float) {

    }

    override fun onCardSwiped(direction: Direction?) {

    }

    override fun onCardRewound() {

    }

    override fun onCardCanceled() {

    }

    override fun onCardAppeared(view: View?, position: Int) {

    }

    override fun onCardDisappeared(view: View?, position: Int) {

    }

    override fun onClickPokemonInformation(pokemon: PokemonResponse) {

    }


}

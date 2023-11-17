package com.victor.balboa.poketinder_2023.ui

import android.os.Bundle

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.victor.balboa.poketinder_2023.R
import com.victor.balboa.poketinder_2023.databinding.ActivityMainBinding


import android.util.Log
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.navigation.Navigation.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator

import com.victor.balboa.poketinder_2023.data.model.PokemonResponse
import com.victor.balboa.poketinder_2023.data.network.PokemonApi
import com.victor.balboa.poketinder_2023.ui.viewmodel.MainViewModel
import com.victor.balboa.poketinder_2023.adapter.PokemonAdapter

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

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

   override fun onCreate(savedInstanceState: Bundle?) {
       super.onCreate(savedInstanceState)

       val navView: BottomNavigationView = binding.navView

       val navController = findNavController(R.id.nav_host_fragment)

       navView.setupWithNavController(navController)
   }
}


/*class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}*/




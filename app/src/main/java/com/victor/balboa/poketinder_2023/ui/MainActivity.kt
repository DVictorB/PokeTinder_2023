package com.victor.balboa.poketinder_2023.ui

import android.os.Bundle

import androidx.navigation.findNavController
import androidx.navigation.ui.setupWithNavController

import com.google.android.material.bottomnavigation.BottomNavigationView
import com.victor.balboa.poketinder_2023.R
import com.victor.balboa.poketinder_2023.databinding.ActivityMainBinding

class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {

    //private val  viewModel: MainActivityViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val navView: BottomNavigationView = binding.navView

        val navController = findNavController(R.id.nav_host_fragment)

        navView.setupWithNavController(navController)
    }
}




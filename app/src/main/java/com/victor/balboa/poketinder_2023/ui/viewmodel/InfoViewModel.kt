package com.victor.balboa.poketinder_2023.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.balboa.poketinder_2023.data.FirebaseRemoteConfigRepository

class InfoViewModel: ViewModel() {

    private var firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()

    init {
        firebaseRemoteConfigRepository.init()
    }

    fun getUrlPokemonLiveData(): MutableLiveData<String> {
        return firebaseRemoteConfigRepository.getUrlPokemonLiveDAta
    }
}
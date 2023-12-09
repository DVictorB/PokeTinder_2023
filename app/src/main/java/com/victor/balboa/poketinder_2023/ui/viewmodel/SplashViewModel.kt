package com.victor.balboa.poketinder_2023.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.balboa.poketinder_2023.data.FirebaseRemoteConfigRepository

class SplashViewModel: ViewModel() {

    private var firebaseRemoteConfigRepository = FirebaseRemoteConfigRepository()

    init {
        firebaseRemoteConfigRepository.init()
    }

    fun getIsUnderMaintenance(): MutableLiveData<Boolean> {
        return firebaseRemoteConfigRepository.isUnderMaintenanceLiveData
    }
}
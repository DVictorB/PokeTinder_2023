package com.victor.balboa.poketinder_2023.data

import androidx.lifecycle.MutableLiveData
import com.google.firebase.remoteconfig.FirebaseRemoteConfig
import com.google.firebase.remoteconfig.get
import com.victor.balboa.poketinder_2023.R

class FirebaseRemoteConfigRepository {

    val instance = FirebaseRemoteConfig.getInstance()

    val getUrlPokemonLiveDAta: MutableLiveData<String> = MutableLiveData()

    val isUnderMaintenanceLiveData: MutableLiveData<Boolean> = MutableLiveData()

    fun init() {
        instance.setDefaultsAsync(R.xml.remote_config_defaults)
        instance.fetchAndActivate().addOnCompleteListener {task ->
            if (task.isSuccessful) {
                getUrlPokemonLiveDAta.value = getUrlPokemoLive()
                isUnderMaintenanceLiveData.value = getIsUnderMaintenance()
            }

        }
    }

    private fun getUrlPokemoLive(): String {
        return instance["url_pokemon_live"].asString()
    }

    private fun getIsUnderMaintenance(): Boolean {
        return instance["is_under_maintance"].asBoolean()
    }
}
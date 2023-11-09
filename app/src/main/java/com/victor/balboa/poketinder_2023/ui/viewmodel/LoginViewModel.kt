package com.victor.balboa.poketinder_2023.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.balboa.poketinder_2023.data.model.User
import com.victor.balboa.poketinder_2023.util.SharedPreferenceUtil

class LoginViewModel(private val context: Context): ViewModel() {

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()

    fun onCreate() {
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun validateInputs(email: String, password: String) {
        if (email.isEmpty() && password.isEmpty()) {
            emptyFieldsError.postValue(true)
        }

        val user: User? = sharedPreferenceUtil.getUser()

        if (email == user?.email && password == user?.password) {
            goSuccessActivity.postValue(true)
        } else {
            fieldsAuthenticateError.postValue(true)
        }
    }
}

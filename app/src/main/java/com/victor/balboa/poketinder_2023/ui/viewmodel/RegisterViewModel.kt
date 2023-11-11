package com.victor.balboa.poketinder_2023.ui.viewmodel

import com.victor.balboa.poketinder_2023.util.SharedPreferenceUtil

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.balboa.poketinder_2023.data.model.User
import java.util.Objects

class RegisterViewModel (private val context: Context): ViewModel(){

    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil

    val emptyFieldsError = MutableLiveData<Boolean>()
    val fieldsAuthenticateError = MutableLiveData<Boolean>()
    val goSuccessActivity = MutableLiveData<Boolean>()
    val passwordConfirmation = MutableLiveData<Boolean>()

    fun onCreate(){
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(context)
        }
    }

    fun validateInputs(name: String, email: String, password: String, password2: String) {
        if (name.isEmpty() || email.isEmpty() || password.isEmpty() || password2.isEmpty()) {
            emptyFieldsError.postValue(true)
        } else if (password !== password2) {
            passwordConfirmation.postValue(true)
        }

        val userId = "1";
        val user = User(
            userId,
            name,
            email,
            password
        )
        sharedPreferenceUtil.saveUser(user)
        goSuccessActivity.postValue(true)
    }
}
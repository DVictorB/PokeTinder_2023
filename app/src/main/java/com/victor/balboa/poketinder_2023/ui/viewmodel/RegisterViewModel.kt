package com.victor.balboa.poketinder_2023.ui.viewmodel

import android.content.Context
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.victor.balboa.poketinder_2023.data.model.User
import com.victor.balboa.poketinder_2023.util.SharedPreferenceUtil

class RegisterViewModel (private val context: Context): ViewModel(){

    //Add to see if works
    private val sharedPreferenceUtil: SharedPreferenceUtil = SharedPreferenceUtil().also {
        it.setSharedPreference(context)
    }

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
        } else if (password != password2) {
            passwordConfirmation.postValue(true)
        } else {
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
}
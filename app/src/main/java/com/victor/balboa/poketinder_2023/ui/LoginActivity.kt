package com.victor.balboa.poketinder_2023.ui

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.victor.balboa.poketinder_2023.util.SharedPreferenceUtil
import com.victor.balboa.poketinder_2023.data.model.User
import com.victor.balboa.poketinder_2023.databinding.ActivityLoginBinding
import com.victor.balboa.poketinder_2023.ui.viewmodel.LoginViewModel

class LoginActivity : BaseActivity<ActivityLoginBinding>(ActivityLoginBinding::inflate) {

    private lateinit var loginViewModel: LoginViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //To start session
        binding.btnLogin.setOnClickListener{
            startLogin()
        }

        loginViewModel = LoginViewModel(this)

        loginViewModel.onCreate()

        loginViewModel.emptyFieldsError.observe(this) {
            Toast.makeText(this, "Ingrese los datos de usuario", Toast.LENGTH_SHORT).show()
        }
        loginViewModel.fieldsAuthenticateError.observe(this) {
            Toast.makeText(this, "Error de usuario", Toast.LENGTH_SHORT).show()
        }

        loginViewModel.goSuccessActivity.observe(this) {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }

    //To login
    fun startLogin(){
        loginViewModel.validateInputs(
            binding.edtEmail.text.toString(),
            binding.edtPassword.text.toString()
        )
    }

    //To register activity
    fun navigateToRegister(view: View) {
        val intent = Intent(this, RegisterActivity::class.java)
        startActivity(intent)
    }

}

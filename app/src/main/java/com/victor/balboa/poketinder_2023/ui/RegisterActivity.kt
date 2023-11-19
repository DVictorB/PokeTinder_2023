package com.victor.balboa.poketinder_2023.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.victor.balboa.poketinder_2023.databinding.ActivityRegisterBinding
import com.victor.balboa.poketinder_2023.ui.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Start register activity
        binding.btnRegister.setOnClickListener {
            startRegister()
        }

        //Go back to login activity
        binding.btnGoLogin.setOnClickListener {
            navigateToLogin()
        }

        registerViewModel = RegisterViewModel(this)

        registerViewModel.onCreate()

        registerViewModel.emptyFieldsError.observe(this){
            Toast.makeText(this,"Ingrese los datos de registro",Toast.LENGTH_SHORT).show()
        }

        registerViewModel.fieldsAuthenticateError.observe(this){
            Toast.makeText(this,"Error de registro",Toast.LENGTH_SHORT).show()
        }

        registerViewModel.passwordConfirmation.observe(this){
            Toast.makeText(this,"Las contraseñas deben coincidir",Toast.LENGTH_SHORT).show()
        }

        registerViewModel.goSuccessActivity.observe(this){
            isValid ->
            if (isValid) {
                //Go to main activity
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
            } else {
                //To validate the user
                Toast.makeText(this,"Complete los campos", Toast.LENGTH_SHORT).show()
                binding.btnRegister.isEnabled = false
            }
        }
    }
    private fun startRegister() {
        val name = binding.edtUserName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val password2 = binding.edtPassword2.text.toString()

        registerViewModel.validateInputs(
            name,
            email,
            password,
            password2)
    }

    //Go back to login activity
    private fun navigateToLogin() {
        val intent = Intent(this, LoginActivity::class.java)
        startActivity(intent)
    }
}
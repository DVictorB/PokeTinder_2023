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

        binding.btnRegister.setOnClickListener {
            startRegister()
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
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun startRegister(){
        val name = binding.edtUserName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val password2 = binding.edtPassword2.text.toString()

        registerViewModel.validateInputs(
            name,
            email,
            password,
            password2
        )
    }
}

//To anterior a Lab08
/*class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {
    private lateinit var sharedPreferenceUtil: SharedPreferenceUtil
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferenceUtil = SharedPreferenceUtil().also {
            it.setSharedPreference(this)
        }
        binding.btnRegister.setOnClickListener{
            val userId = "1"
            val userName = binding.edtUserName.text.toString()
            val email = binding.edtEmail.text.toString()
            val password = binding.edtPassword.text.toString()

            val user = User(
                userId,
                userName,
                email,
                password
            )
            sharedPreferenceUtil.saveUser(user)
            startActivity(Intent(this, LoginActivity::class.java))
        }
        binding.btnGoLogin.setOnClickListener{
        }
    }
}*/

/*
package com.jose.arcos.poketinder.ui.view
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import com.jose.arcos.poketinder.databinding.ActivityRegisterBinding
import com.jose.arcos.poketinder.ui.viewmodel.RegisterViewModel

class RegisterActivity : BaseActivity<ActivityRegisterBinding>(ActivityRegisterBinding::inflate) {

    private lateinit var registerViewModel: RegisterViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnRegister.setOnClickListener {
            startRegister()
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
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
    }
    fun startRegister(){
        val name = binding.edtUserName.text.toString()
        val email = binding.edtEmail.text.toString()
        val password = binding.edtPassword.text.toString()
        val password2 = binding.edtPassword2.text.toString()

        registerViewModel.validateInputs(
            name,
            email,
            password,
            password2
        )
    }
}
*/

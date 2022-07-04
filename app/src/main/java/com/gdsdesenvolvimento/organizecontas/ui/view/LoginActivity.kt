package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.LoginDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.RegisterDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.auth.UserLoggedDS
import com.gdsdesenvolvimento.organizecontas.data.dataSource.instances.FBInstance
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.repository.AuthenticatorRepository
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityLoginBinding
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.LoginViewModel
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ViewModelFactory
import com.gdsdesenvolvimento.organizecontas.utils.extensions.afterTextChanged
import com.gdsdesenvolvimento.organizecontas.utils.extensions.setEditError
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel = ViewModelProvider(
            this,
            DI.getViewModelFactory()
        )[LoginViewModel::class.java]
        initComponents()
        observers()
    }

    private fun initComponents() {
        binding.emailLogin.afterTextChanged {
            viewModel.formLoginDataChanged(
                binding.emailLogin.text.toString(),
                binding.senhaLogin.text.toString()
            )
        }
        binding.senhaLogin.afterTextChanged {
            viewModel.formLoginDataChanged(
                binding.emailLogin.text.toString(),
                binding.senhaLogin.text.toString()
            )
        }
    }

    private fun observers() {
        viewModel.loginFormState.observe(this){
            when(it){
                is FormState.ErrorEmail.ErrorEmpty->{
                    setEditError(binding.emailLogin,it.msg)
                }
                is FormState.ErrorEmail.ErrorFormatInvalid->{
                    setEditError(binding.emailLogin,it.msg)
                }
                is FormState.ErrorPassword.ErrorEmpty->{
                    setEditError(binding.senhaLogin,it.msg)

                }
                is FormState.ErrorPassword.ErrorFormatInvalid->{
                    setEditError(binding.senhaLogin,it.msg)
                }
                is FormState.Success->{
                    binding.btnLogin.isEnabled = true

                }
            }
        }
    }
}
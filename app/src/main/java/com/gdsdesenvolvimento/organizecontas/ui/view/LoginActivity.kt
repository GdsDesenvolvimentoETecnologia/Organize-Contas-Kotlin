package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.data.model.User
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityLoginBinding
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.LoginViewModel
import com.gdsdesenvolvimento.organizecontas.utils.extensions.*
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding
    private lateinit var viewModel: LoginViewModel
    private lateinit var userLogin: User
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActivity()
        observers()
    }

    private fun setupActivity() {
        viewModel = ViewModelProvider(
            this,
            DI.getViewModelFactory()
        )[LoginViewModel::class.java]
        initComponents()
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
        binding.btnLogin.setOnClickListener {
            viewModel.loginEmailWithPassword(userLogin)
        }
    }

    private fun observers() {
        viewModel.loginFormState.observe(this) {
            when (it) {
                is FormState.ErrorEmail.ErrorEmpty -> {
                    setEditError(binding.emailLogin, it.msg)
                }
                is FormState.ErrorEmail.ErrorFormatInvalid -> {
                    setEditError(binding.emailLogin, it.msg)
                }
                is FormState.ErrorPassword.ErrorEmpty -> {
                    setEditError(binding.senhaLogin, it.msg)
                }
                is FormState.ErrorPassword.ErrorFormatInvalid -> {
                    setEditError(binding.senhaLogin, it.msg)
                }
                is FormState.Success -> {
                    userLogin = DI.getUseLogin(it.data.email, it.data.password)
                    binding.btnLogin.isEnabled = true
                }
            }
        }
        viewModel.userResult.observe(this) {
            when (it) {
                is FormResult.Success -> {
                    dialog(this, "Logado", "PArabens voce esta logado ", true) {
                        nextScreen(MainActivity())
                    }
                }
                is FormResult.Error -> {
                    dialog(
                        this,
                        getString(R.string.falha),
                        getString(R.string.falha_ao_fazer_login),
                        true
                    ) {
                        finish()
                    }
                }
                is FormResult.Loading -> {
                    message(getString(R.string.carregando))
                }
            }
        }
    }
}
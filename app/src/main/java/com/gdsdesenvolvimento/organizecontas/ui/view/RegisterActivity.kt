package com.gdsdesenvolvimento.organizecontas.ui.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.EditText
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.whenResumed
import com.gdsdesenvolvimento.organizecontas.data.di.DI
import com.gdsdesenvolvimento.organizecontas.databinding.ActivityRegisterBinding
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.RegisterViewModel
import com.gdsdesenvolvimento.organizecontas.ui.viewmodel.ViewModelFactory
import com.gdsdesenvolvimento.organizecontas.utils.extensions.afterTextChanged
import com.gdsdesenvolvimento.organizecontas.utils.extensions.dialog
import com.gdsdesenvolvimento.organizecontas.utils.extensions.message
import com.gdsdesenvolvimento.organizecontas.utils.extensions.nextScreen
import com.gdsdesenvolvimento.organizecontas.utils.results.FormResult
import com.gdsdesenvolvimento.organizecontas.utils.state.FormState
import com.gdsdesenvolvimento.organizecontas.utils.state.RegisterFormState

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding
    private lateinit var viewModel: RegisterViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActivity()
        observers()
    }

    private fun setupActivity() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(DI.getAuthRepository())
        )[RegisterViewModel::class.java]
        initComponents()
    }

    private fun initComponents() = with(binding) {
        editName.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editEmail.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editPassword.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editConfirmPassword.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        editPhone.afterTextChanged {
            viewModel.formRegisterDataChange(
                binding.editName.text.toString(),
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString(),
                binding.editConfirmPassword.text.toString(),
                binding.editPhone.text.toString()
            )
        }
        button.setOnClickListener {
            viewModel.registerEmailWithPassword(
                binding.editEmail.text.toString(),
                binding.editPassword.text.toString()
            )
        }
    }

    private fun observers() {
        val nome = binding.editName
        val email = binding.editEmail
        val senha = binding.editPassword
        val confirmSenha = binding.editConfirmPassword
        val phone = binding.editPhone
        viewModel.registerFormState.observe(this) { result ->
            when (result) {
                is RegisterFormState.ErrorEmpty.ErrorNameEmpty -> {
                    setError(nome, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorEmailEmpty -> {
                    setError(email, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorPasswordEmpty -> {
                    setError(senha, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorConfirmPasswordEmpty -> {
                    setError(confirmSenha, result.msg)
                }
                is RegisterFormState.ErrorEmpty.ErrorPhoneEmpty -> {
                    setError(phone, result.msg)
                }
                is RegisterFormState.ErrorQtdCharactersInvalid -> {
                    setError(nome, result.msg)
                }
                is RegisterFormState.ErrorEmailNotValid -> {
                    setError(phone, result.msg)
                }
                is RegisterFormState.ErrorPasswordInvalid-> {
                    setError(senha, result.msg)
                }
                is RegisterFormState.ErrorPasswordNotEquals -> {
                    setError(confirmSenha, result.msg)
                }
                is RegisterFormState.ErrorPhoneInvalid -> {
                    setError(phone, result.msg)
                }

            }
        }
        viewModel.formResult.observe(this) { result ->
            when (result) {
                is FormResult.Success -> {
                    dialog(
                        this,
                        "Bem vindo novo integrante",
                        "Seja bem vindo ao melhor app de gestao de financas mane",
                        true
                    ) {
                        nextScreen(MainActivity())
                    }
                }
                is FormResult.Error -> {
                    dialog(this, "Falha", "Nao foi possivel cadastrar -> ${result.msg}", true) {
                        finish()
                    }
                }
                is FormResult.Loading -> {
                    message("Carregando")
                }
            }

        }
    }

    private fun setError(edit: EditText, msg: String) {
        edit.error = msg
    }
}
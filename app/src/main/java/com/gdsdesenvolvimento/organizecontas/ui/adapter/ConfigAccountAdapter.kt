package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountConfig
import com.gdsdesenvolvimento.organizecontas.data.model.UserRegister
import com.gdsdesenvolvimento.organizecontas.data.repository.RealtimeRepository
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemAccountBinding
import com.gdsdesenvolvimento.organizecontas.utils.extensions.*
import com.gdsdesenvolvimento.organizecontas.utils.results.FinishItemResult
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import javax.security.auth.callback.Callback

class ConfigAccountAdapter(
    private var numbersForms: Int,
    private val idUserLogged: String,
    private val realtimeRepository: RealtimeRepository,
    private val callback: FinishItemResult
) : RecyclerView.Adapter<ConfigAccountAdapter.ConfigAccountViewHolder>() {

    private lateinit var item: ItemAccountConfig

    inner class ConfigAccountViewHolder(val binding: RvItemAccountBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigAccountViewHolder {
        return ConfigAccountViewHolder(
            RvItemAccountBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = numbersForms
    override fun onBindViewHolder(holder: ConfigAccountViewHolder, position: Int) {
        holder.apply {
            bind(
                this,
                position,
                this.binding.nomeBanco,
                this.binding.ckLimite,
                this.binding.valorConta,
                this.binding.valorLimite,
                this.binding.btnSalvarForm
            )
        }
        if (numbersForms == 0){
            callback.finish()
        }
    }

    private fun bind(
        holder: ConfigAccountViewHolder,
        position: Int,
        nomeBanco: EditText,
        ckLimite: CheckBox,
        valorConta: EditText,
        valorLimite: EditText,
        btnSalvarForm: Button
    ) {
        nomeBanco.afterTextChanged { safeName ->
            validaNome(holder, safeName)
        }

        ckLimite.setOnClickListener {
            setContaLimit(holder, valorLimite)
        }

        valorConta.afterTextChanged { safeValorConta ->
            verifyNumberValue(
                holder,
                valorConta,
                safeValorConta
            )
        }

        valorLimite.afterTextChanged { safeValorLimite ->
            verifyNumberValue(
                holder,
                valorLimite,
                safeValorLimite
            )
        }
        btnSalvarForm.setOnClickListener {
            if (numbersForms == 1){
                saveDataFormAccount(nomeBanco, valorConta, ckLimite, valorLimite, position)
                callback.finish()
            }else{
                saveDataFormAccount(nomeBanco, valorConta, ckLimite, valorLimite, position)
            }
        }

    }

    private fun saveDataFormAccount(
        nomeBanco: EditText,
        valorConta: EditText,
        ckLimite: CheckBox,
        valorLimite: EditText,
        position: Int
    ) {
        item = ItemAccountConfig(
            nomeBanco.text.toString(),
            valorConta.extractDouble(),
            ckLimite.isChecked,
            valorLimite.extractDouble()
        )
        saveFirebase(position)
        numbersForms -= 1
        notifyItemRemoved(0)
    }

    private fun saveFirebase(position: Int) {
        GlobalScope.launch {
            realtimeRepository.saveAccountsConfig(position, idUserLogged, item)
                .addOnSuccessListener {
                    val a = 1
                }
                .addOnFailureListener {
                    val a = 1
                }
        }
    }

    private fun validaNome(holder: ConfigAccountViewHolder, nomeBanco: String) {
        if (nomeBanco.isEmpty()) {
            holder.binding.nomeBanco.error = CAMPO_EM_BRANCO
            holder.binding.btnSalvarForm.disable()
        } else if (nomeBanco.length < 2) {
            holder.binding.nomeBanco.error = INVALIDO
            holder.binding.btnSalvarForm.disable()
        }

    }

    private fun setContaLimit(holder: ConfigAccountViewHolder, valorLimite: EditText) {
        if (holder.binding.ckLimite.isChecked) {
            valorLimite.show()
            holder.binding.btnSalvarForm.disable()
        } else {
            valorLimite.hide()
            holder.binding.btnSalvarForm.enabled()
        }
    }

    private fun verifyNumberValue(
        holder: ConfigAccountViewHolder,
        valorConta: EditText,
        value: String
    ) {
        when {
            value.isEmpty() -> {
                valorConta.error = CAMPO_EM_BRANCO
                holder.binding.btnSalvarForm.disable()
            }
            value == "-" -> {
                return
            }
            value.toDouble() == 0.00 -> {
                setTextColor(valorConta, Color.BLACK)
                holder.binding.btnSalvarForm.enabled()
            }
            value.toDouble() < 0.00 -> {
                setTextColor(valorConta, Color.RED)
                holder.binding.btnSalvarForm.enabled()
            }
            value.toDouble() > 0.00 -> {
                setTextColor(valorConta, Color.GREEN)
                holder.binding.btnSalvarForm.enabled()
            }
        }
    }

    private fun setTextColor(editText: EditText, color: Int) {
        editText.setTextColor(color)
    }

    companion object {
        const val CAMPO_EM_BRANCO = "Campo em branco"
        const val INVALIDO = "Invalido"
    }
}

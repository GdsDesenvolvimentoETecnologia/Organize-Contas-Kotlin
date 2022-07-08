package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.R
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemAccountBinding
import com.gdsdesenvolvimento.organizecontas.utils.extensions.afterTextChanged
import com.gdsdesenvolvimento.organizecontas.utils.extensions.extractDouble

class ConfigAccountAdapter(
    private val numbersForms: Int
) : RecyclerView.Adapter<ConfigAccountAdapter.ConfigAccountViewHolder>() {
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
        val nomeBanco = holder.binding.nomeBanco
        val ckLimite = holder.binding.ckLimite
        val valorConta = holder.binding.valorConta
        val valorLimite = holder.binding.valorLimite
        validandoFormulario(holder, nomeBanco, ckLimite, valorConta, valorLimite)
    }

    private fun validandoFormulario(
        holder: ConfigAccountViewHolder,
        nomeBanco: EditText,
        ckLimite: CheckBox,
        valorConta: EditText,
        valorLimite: EditText
    ) {
        nomeBanco.afterTextChanged { validaNome(holder, it) }
        valorConta.afterTextChanged { validaValorDaConta(holder, valorConta.extractDouble()) }
        if (validaCk(holder, ckLimite)) {
            valorLimite.afterTextChanged { validaValorLimite(holder, valorLimite.extractDouble()) }
        }
    }

    private fun validaNome(holder: ConfigAccountViewHolder, nomeBanco: String) {
        if (nomeBanco.isEmpty()) {
            holder.binding.nomeBanco.error = "Campo em branco"
        } else if (nomeBanco.length < 2) {
            holder.binding.nomeBanco.error = "Invalido"
        }
    }

    private fun validaValorDaConta(holder: ConfigAccountViewHolder, valorConta: Double) {
        defineTextColor(valorConta, holder.binding.valorConta)
    }

    private fun validaCk(holder: ConfigAccountViewHolder, ckLimite: CheckBox): Boolean {
        return true
    }

    private fun validaValorLimite(
        holder: ConfigAccountViewHolder,
        valorLimite: Double
    ) {
        defineTextColor(valorLimite, holder.binding.valorConta)
    }

    @SuppressLint("ResourceAsColor")
    fun defineTextColor(valor: Double, editText: EditText) {
        when {
            valor == 0.0 -> {
                editText.setTextColor(R.color.black)
            }
            valor <= 0.0 -> {
                editText.setTextColor(R.color.my_green)
            }
            valor >= 0.0 -> {
                editText.setTextColor(R.color.my_red)
            }
        }
    }

}

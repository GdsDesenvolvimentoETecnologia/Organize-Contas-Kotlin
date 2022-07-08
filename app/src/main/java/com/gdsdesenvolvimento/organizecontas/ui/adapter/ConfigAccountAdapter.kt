package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountConfig
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemAccountBinding
import com.gdsdesenvolvimento.organizecontas.utils.extensions.*

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
        holder.apply {
            bind(
                this,
                this.binding.nomeBanco,
                this.binding.ckLimite,
                this.binding.valorConta,
                this.binding.valorLimite,
                this.binding.btnSalvarForm
            )

        }
    }

    private fun bind(
        holder: ConfigAccountViewHolder,
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
            val formAccount = ItemAccountConfig(
                nomeBanco.text.toString(),
                valorConta.extractDouble(),
                ckLimite.isChecked,
                valorLimite.extractDouble()
            )
            holder.binding.cardView.gone()
        }

    }

    private fun validaNome(holder: ConfigAccountViewHolder, nomeBanco: String) {
        if (nomeBanco.isEmpty()) {
            holder.binding.nomeBanco.error = "Campo em branco"
            holder.binding.btnSalvarForm.disable()
        } else if (nomeBanco.length < 2) {
            holder.binding.nomeBanco.error = "Invalido"
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
                valorConta.error = "Campo necessario"
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
}

package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.data.model.ItemAccountForm
import com.gdsdesenvolvimento.organizecontas.databinding.ItemPrincipalBinding

class MainAdapterAccount(
    private val listAccount: List<ItemAccountForm>
) : RecyclerView.Adapter<MainAdapterAccount.MainViewHolder>() {
    inner class MainViewHolder(val binding: ItemPrincipalBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(
            ItemPrincipalBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun getItemCount(): Int = listAccount.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val itemAccountForm = listAccount[position]
        holder.apply {
            bind(holder.binding, itemAccountForm)
        }
    }

    private fun bind(
        binding: ItemPrincipalBinding,
        itemAccountForm: ItemAccountForm
    ) {
        binding.bankName.text = itemAccountForm.nomeDoBanco
        binding.valorInicial.text = itemAccountForm.valorNaConta.toString()
        if (itemAccountForm.possuiLimite) {
            binding.valorRestante.text =
                valorRestante(itemAccountForm.valorNaConta, itemAccountForm.valorLimite!!)
        }
    }

    private fun valorRestante(valorNaConta: Double, valorLimite: Double): CharSequence? {
        val result = valorNaConta + valorLimite
        return result.toString()
    }
}
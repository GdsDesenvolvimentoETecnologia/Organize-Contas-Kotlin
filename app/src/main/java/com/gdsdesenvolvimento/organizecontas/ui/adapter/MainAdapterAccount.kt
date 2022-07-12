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
            bind(holder.binding,itemAccountForm,position)
        }
    }

    private fun bind(
        binding: ItemPrincipalBinding,
        itemCreditCardForm: ItemAccountForm,
        position: Int
    ) {

    }
}
package com.gdsdesenvolvimento.organizecontas.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemCreditCardBinding

class ConfigCreditCardAdapter :
    RecyclerView.Adapter<ConfigCreditCardAdapter.ConfigCreditCardViewHolder>() {
    inner class ConfigCreditCardViewHolder(private val binding: RvItemCreditCardBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ConfigCreditCardViewHolder {
        return ConfigCreditCardViewHolder(
            RvItemCreditCardBinding.inflate(
                LayoutInflater.from(parent.context), parent, false
            )
        )
    }

    override fun onBindViewHolder(holder: ConfigCreditCardViewHolder, position: Int) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
        TODO("Not yet implemented")
    }
}
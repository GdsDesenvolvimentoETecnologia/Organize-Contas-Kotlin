package com.gdsdesenvolvimento.organizecontas.utils.helper

import android.graphics.Color
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemAccountBinding
import com.gdsdesenvolvimento.organizecontas.databinding.RvItemCreditCardBinding
import com.gdsdesenvolvimento.organizecontas.utils.extensions.disable
import com.gdsdesenvolvimento.organizecontas.utils.extensions.enabled
import com.gdsdesenvolvimento.organizecontas.utils.extensions.hide
import com.gdsdesenvolvimento.organizecontas.utils.extensions.show

object ValidateAdapterForm {

    const val CAMPO_EM_BRANCO = "Campo em branco"
    const val INVALIDO = "Invalido"

    fun validateBankName(button: Button,editText: EditText, bankName: String) {
        if (bankName.isEmpty()) {
            editText.error = CAMPO_EM_BRANCO
            button.disable()
        } else if (bankName.length < 2) {
            editText.error = INVALIDO
            button.disable()
        }
    }

    fun ckIsChecked(ck: CheckBox, button: Button, editText: EditText) {
        if (ck.isChecked) {
            editText.show()
            button.disable()
        } else {
            editText.hide()
            button.enabled()
        }
    }

    fun verifyNumberValue(
        button: Button,
        editText: EditText,
        value: String
    ) {
        when {
            value.isEmpty() -> {
                editText.error = CAMPO_EM_BRANCO
                button.disable()
            }
            value == "-" -> {
                return
            }
            value.toDouble() == 0.00 -> {
                setTextColor(editText, Color.BLACK)
                button.enabled()
            }
            value.toDouble() < 0.00 -> {
                setTextColor(editText, Color.RED)
                button.enabled()
            }
            value.toDouble() > 0.00 -> {
                setTextColor(editText, Color.GREEN)
                button.enabled()
            }
        }
    }

    private fun setTextColor(editText: EditText, color: Int) {
        editText.setTextColor(color)
    }

}
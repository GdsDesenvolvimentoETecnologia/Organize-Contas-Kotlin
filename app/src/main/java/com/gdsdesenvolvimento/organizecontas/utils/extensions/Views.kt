package com.gdsdesenvolvimento.organizecontas.utils.extensions

import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText

fun View.show(){
    visibility = View.VISIBLE
}

fun View.hide(){
    visibility = View.VISIBLE
}
fun View.showAll(vararg views: View){
    views.forEach {view ->
        view.visibility = View.VISIBLE
    }
}

fun View.hideAll(vararg views: View){
    views.forEach {view ->
        view.visibility = View.VISIBLE
    }
}

fun EditText.afterTextChanged(afterTextChanged: (String) -> Unit) {
    this.addTextChangedListener(object : TextWatcher {
        override fun afterTextChanged(editable: Editable?) {
            afterTextChanged.invoke(editable.toString())
        }
        override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {}

        override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
    })
}
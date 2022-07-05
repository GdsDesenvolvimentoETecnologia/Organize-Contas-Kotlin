package com.gdsdesenvolvimento.organizecontas.utils.extensions

import android.content.Context
import android.content.Intent
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.gdsdesenvolvimento.organizecontas.data.di.DI

const val Ok = "OK"
fun AppCompatActivity.nextScreen(activity: AppCompatActivity) {
    Intent(this, activity::class.java).apply {
        startActivity(this)
    }
}

fun Fragment.nextScreen(activity: AppCompatActivity) {
    Intent(activity.applicationContext, activity::class.java).apply {
        startActivity(this)
    }
}

fun AppCompatActivity.dialog(
    context: Context,
    title: String,
    msg: String,
    isCancelable: Boolean = true,
    action: () -> Unit
) {
    AlertDialog.Builder(context)
        .setTitle(title)
        .setMessage(msg)
        .setCancelable(isCancelable)
        .setPositiveButton(Ok) { _, _ ->
            action()
        }
        .show()
}

fun AppCompatActivity.message(msg: String) {
    Toast.makeText(this, msg, Toast.LENGTH_LONG).show()
}

fun AppCompatActivity.setEditError(editText: EditText, msg: String) {
    editText.error = msg
}
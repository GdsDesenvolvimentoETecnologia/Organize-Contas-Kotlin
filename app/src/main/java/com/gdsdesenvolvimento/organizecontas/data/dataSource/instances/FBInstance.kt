package com.gdsdesenvolvimento.organizecontas.data.dataSource.instances

import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

object FBInstance {
    fun getAuthenticator(): FirebaseAuth {
        return Firebase.auth
    }
    fun getRealtimeDatabase() : DatabaseReference {
        return FirebaseDatabase.getInstance().reference
    }
}
package com.saldivar.retoandroid.data

import com.google.firebase.firestore.FirebaseFirestore

object FirestoreInstance {
    fun dbFirestore() = FirebaseFirestore.getInstance()
}
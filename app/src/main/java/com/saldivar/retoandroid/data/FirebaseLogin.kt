package com.saldivar.retoandroid.data

import com.saldivar.retoandroid.domain.usecase.LoginUseCase

class FirebaseLogin:LoginUseCase{
    override fun checkUser(user: String, password: String): Boolean {
        var response: Boolean ?= null
        FirestoreInstance.dbFirestore().collection("USUARIOS").whereEqualTo("user", user).whereEqualTo("password",password).get().addOnSuccessListener {
            response = it.size() != 0
        }
        return response ?: false
    }

    override fun createUser(user: String, password: String): Boolean {
        var response: Boolean ?= null
            val data = hashMapOf(
                "user" to user,
                "password" to password
            )
        FirestoreInstance.dbFirestore().collection("USUARIOS").add(data).addOnSuccessListener {
                response = true
            }.addOnFailureListener {
                response = false
            }
        return response ?: false

    }

}
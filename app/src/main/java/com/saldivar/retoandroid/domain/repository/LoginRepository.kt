package com.saldivar.retoandroid.domain.repository

import com.saldivar.retoandroid.data.FirebaseLogin
import javax.inject.Inject

class LoginRepository @Inject constructor(private val firebase: FirebaseLogin) {
     fun checkUser(user:String, password:String) = firebase.checkUser(user, password)
     fun createUser(user:String, password:String) = firebase.createUser(user, password)
}
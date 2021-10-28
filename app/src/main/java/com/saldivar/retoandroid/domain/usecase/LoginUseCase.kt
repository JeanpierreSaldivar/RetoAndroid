package com.saldivar.retoandroid.domain.usecase

interface LoginUseCase {
    fun checkUser(user: String,password: String): Boolean
    fun createUser(user: String,password: String): Boolean
}
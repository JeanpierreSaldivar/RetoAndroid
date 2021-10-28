package com.saldivar.retoandroid.utils

open class FactoryError() {
    fun getError(type: Int):String{
        return when(type){
            ErrorType.UserError.type -> "Ingrese el usuario"
            ErrorType.PasswordError.type -> "Ingrese el password"
            ErrorType.IncorrectData.type -> "Usuario o contraseña no validos"
            ErrorType.CreateUserError.type -> "Hubo un error al crear usuario"
            else-> "Usuario o contraseña no validos"
        }

    }
}

enum class ErrorType(val type: Int) {
    UserError(1),
    PasswordError(2),
    IncorrectData(3),
    CreateUserError(4),
}
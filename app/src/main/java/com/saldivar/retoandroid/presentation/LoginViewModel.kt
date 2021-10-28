package com.saldivar.retoandroid.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.saldivar.retoandroid.domain.repository.LoginRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import javax.inject.Inject
import com.saldivar.retoandroid.core.Result

@HiltViewModel
class LoginViewModel@Inject constructor(private val repo: LoginRepository) : ViewModel()  {
    fun checkUser(user:String,password:String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.checkUser(user,password)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }

    fun createUser(user:String,password:String) = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
        emit(Result.Loading())
        try {
            emit(Result.Success(repo.createUser(user,password)))
        } catch (e: Exception) {
            emit(Result.Failure(e))
        }
    }
}
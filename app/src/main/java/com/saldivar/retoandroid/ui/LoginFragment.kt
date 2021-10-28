package com.saldivar.retoandroid.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.saldivar.retoandroid.R
import com.saldivar.retoandroid.databinding.FragmentLoginBinding
import com.saldivar.retoandroid.presentation.LoginViewModel
import com.saldivar.retoandroid.utils.ErrorType
import com.saldivar.retoandroid.utils.FactoryError
import dagger.hilt.android.AndroidEntryPoint
import com.saldivar.retoandroid.core.Result

@AndroidEntryPoint
class LoginFragment : Fragment() {
    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private val loginViewModel: LoginViewModel by viewModels()

    private var userData : String? = null
    private var passwordData : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        clickEvents()
        return binding.root
    }

    private fun clickEvents() {
        binding.apply{
            appCompatButtonInto.setOnClickListener{
                validateUser()
                validatePassword()
                consultCheckUser()
            }
        }
    }

    private fun consultCheckUser() {
        if (!userData.isNullOrEmpty() && !passwordData.isNullOrEmpty()){
            loginViewModel.checkUser(userData!!, passwordData!!).observe(viewLifecycleOwner,{ result->
                when(result){
                    is Result.Loading ->{
                        //eventos  visuales mientras carga la data
                    }
                    is Result.Success ->{
                        //nextFragment
                    }
                    is Result.Failure->{
                        userSetColorError()
                        passwordSetColorError()
                        val message = FactoryError().getError(ErrorType.IncorrectData.type)
                        binding.appCompatTextViewError.text = message
                    }
                }
            })
        }
    }

    private fun validatePassword() {
        val password = binding.etPassword.text.toString()
        this.passwordData = if (password.isEmpty() || password.isBlank()){
            val message = FactoryError().getError(ErrorType.PasswordError.type)
            binding.appCompatTextViewError.text = message
            passwordSetColorError()
            null
        }else password
    }

    private fun passwordSetColorError() {
        binding.apply {
            textInputLayoutPassword.setBackgroundResource(R.drawable.box_input_error)
            imgGroup.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_error))
        }
    }

    private fun userSetColorError() {
        binding.apply {
            textInputLayoutUser.setBackgroundResource(R.drawable.box_input_error)
            imgGroupUser.setImageDrawable(ContextCompat.getDrawable(requireActivity(), R.drawable.ic_error))
            imgGroupUser.visibility = View.VISIBLE
        }
    }

    private fun validateUser() {
        val user = binding.etUser.text.toString()
        this.userData = if (user.isEmpty() || user.isBlank()){
            val message = FactoryError().getError(ErrorType.UserError.type)
            binding.appCompatTextViewError.text = message
            userSetColorError()
            null
        }else user
    }



}
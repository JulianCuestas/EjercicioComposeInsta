package com.jcinstagram.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jcinstagram.login.domain.LoginUseCase
import kotlinx.coroutines.launch

private const val TAG = "LoginViewModel"

class LoginViewModel: ViewModel() {

    var loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    val email: LiveData<String> = _email

    private val _password = MutableLiveData<String>()
    val password: LiveData<String> = _password

    private val _isLoginEnable = MutableLiveData<Boolean>()
    val isLoginEnable: LiveData<Boolean> = _isLoginEnable

    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading: LiveData<Boolean> = _isLoading

    fun onLoginChanged(emailIn: String, passwordIn: String) {
        _email.value = emailIn
        _password.value = passwordIn
        _isLoginEnable.value = enableLogin(emailIn, passwordIn)
    }

    private fun enableLogin(email: String, password: String): Boolean =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 6

    fun onLoginSelected() {
        viewModelScope.launch {
            _isLoading.value = true
            val result = loginUseCase(email.value!!, password.value!!)
            if(result) {
                //Navegar a la sig. pantalla
                Log.i(TAG,"Welcome to home. Session OK.")
            }
            _isLoading.value = false
        }
    }
}
package com.leandrolcd.loginexample.ui.login.ui

import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel: ViewModel() {
    //region Fields
    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _isLoginEnabled = MutableLiveData<Boolean>()
    //endregion

    //region Properties
    val email : LiveData<String> = _email
    val password : LiveData<String> = _password
    val isLoginEnabled : LiveData<Boolean> = _isLoginEnabled
    //endregion

    //region Methods
    fun onLoginChange(email:String, pwd:String){
        _email.value = email
        _password.value = pwd
        _isLoginEnabled.value = enabledLogin(email = email, password = pwd)
    }
   private fun enabledLogin(email:String, password:String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5
    //region
}
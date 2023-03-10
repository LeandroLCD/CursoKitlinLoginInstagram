package com.leandrolcd.loginexample.ui.login.ui

import android.util.Log
import android.util.Patterns
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.leandrolcd.loginexample.ui.login.domain.LoginUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val loginUseCase: LoginUseCase): ViewModel() {
    //region Fields
    //val loginUseCase = LoginUseCase()

    private val _email = MutableLiveData<String>()
    private val _password = MutableLiveData<String>()
    private val _isLoginEnabled = MutableLiveData<Boolean>()
    private val _isLoading = MutableLiveData<Boolean>()
    //endregion

    //region Properties
    val email : LiveData<String> = _email
    val password : LiveData<String> = _password
    val isLoginEnabled : LiveData<Boolean> = _isLoginEnabled
    val isLoading : LiveData<Boolean> = _isLoading
    //endregion

    //region Methods
    fun onLoginChange(email:String, pwd:String){
        _email.value = email
        _password.value = pwd
        _isLoginEnabled.value = enabledLogin(email = email, password = pwd)
    }

    fun onLoginCliked(){
        viewModelScope.launch {
            _isLoading.value = true;
            val result = loginUseCase(email.value!!, password.value!! )
            if(result){
                //Navegar a la app- otra pagina

            }
            Log.i("login","Result Ok ${result.toString()}")
            _isLoading.value = false
        }
    }

   private fun enabledLogin(email:String, password:String) =
        Patterns.EMAIL_ADDRESS.matcher(email).matches() && password.length > 5
    //region
}
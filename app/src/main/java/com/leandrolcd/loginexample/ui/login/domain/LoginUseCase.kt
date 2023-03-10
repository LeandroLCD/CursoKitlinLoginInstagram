package com.leandrolcd.loginexample.ui.login.domain

import com.leandrolcd.loginexample.ui.login.data.repository.LoginRepository
import javax.inject.Inject

class LoginUseCase @Inject constructor(private val repository: LoginRepository) {
    //private val repository = LoginRepository()
    suspend operator fun invoke(user:String, password:String):Boolean{
        return repository.doLogin(user, password)
    }
}
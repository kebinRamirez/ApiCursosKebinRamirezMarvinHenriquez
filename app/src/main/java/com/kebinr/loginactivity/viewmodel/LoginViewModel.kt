package com.kebinr.loginactivity.viewmodel

import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Repositorios.LoginInfo

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginInfo
    fun getLogged() = LoginInfo.getLogged()
    fun setLogged(state: Boolean){
        LoginInfo.setLogged(state)
    }
}
package com.kebinr.loginactivity.viewmodel

import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Data.User
import com.kebinr.loginactivity.Repositorios.LoginInfo

class LoginViewModel : ViewModel() {
    private val loginRepository = LoginInfo
    fun getLogged() = LoginInfo.getLogged()
    fun getUser()= LoginInfo.stateuser
    fun signIn(email: String, clave: String, usuario : String) =
        loginRepository.signIn(User(email, clave, usuario, usuario,"",""))
    fun signUp(email: String, clave: String, usuario : String) =
        loginRepository.signUp(User(email, clave, usuario, usuario,"",""))
    fun setLogged(state: Boolean){
        LoginInfo.setLogged(state)
    }
    fun setUserInfo(user: String, token: String){
        LoginInfo.setUser(user, token)
    }
}
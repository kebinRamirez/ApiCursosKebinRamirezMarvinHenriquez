package com.kebinr.loginactivity.Repositorios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kebinr.loginactivity.Data.User
import com.kebinr.loginactivity.Repositorios.api.ApiLogin.LoginApiService
import com.kebinr.loginactivity.util.PreferenceProvider

object LoginInfo {
    var logged = MutableLiveData<Boolean>()
    var stateLogged : Boolean = false
    var User = MutableLiveData<String>()
    var stateuser : String ="-1;-1"
    private val service =
        LoginApiService()

    init {
        stateLogged = PreferenceProvider.getValue()!!
        logged.value = stateLogged
        stateuser = PreferenceProvider.getUserInfo()!!
        User.value = stateuser

    }

    fun getLogged() = logged as LiveData<Boolean>
    fun signIn(user: User) = service.signIn(user)
    fun signUp(user: User) = service.signUp(user)

    fun setLogged(state: Boolean){
        stateLogged = state
        logged.value = stateLogged
        PreferenceProvider.setValue(state)
    }
    fun setUser(user: String, token: String){
        stateuser = user +";"+token
        User.value = stateuser
        PreferenceProvider.setUserInfo(user +";"+token)
    }
}


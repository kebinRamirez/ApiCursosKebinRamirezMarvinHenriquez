package com.kebinr.loginactivity.viewmodel

import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Data.User
import com.kebinr.loginactivity.Repositorios.UsersInfo

class UsersInfoViewModel : ViewModel(){
    private val myRepository = UsersInfo
    fun getUsers() = UsersInfo.getUsers()
    fun addUser(user: User) =
        UsersInfo.addUser(user)
}
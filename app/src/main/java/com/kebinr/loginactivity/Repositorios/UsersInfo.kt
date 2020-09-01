package com.kebinr.loginactivity.Repositorios

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.kebinr.loginactivity.Data.User

object UsersInfo {
    private var userList = mutableListOf<User>()
    private val users = MutableLiveData<List<User>>()

    fun getUsers() = users as LiveData<List<User>>

    fun addUser(user: User) {
        userList.add(user)
        users.value =
            userList
    }
}
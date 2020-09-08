package com.kebinr.loginactivity.Repositorios

import com.kebinr.loginactivity.Repositorios.api.ToDosApiService

class ToDotInfo {
    private val apiService = ToDosApiService()

    suspend fun getToDo(index : Int) = apiService.getToDo(index)

    suspend fun deleteToDo(index : Int) = apiService.deleteToDo(index)
}
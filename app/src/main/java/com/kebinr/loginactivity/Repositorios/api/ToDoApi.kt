package com.kebinr.loginactivity.Repositorios.api

import com.kebinr.loginactivity.Repositorios.api.DataAPi.ToDo
import retrofit2.http.*

interface ToDosApi {

    @GET("todos/{index}/")
    suspend fun getToDo(@Path("index") index: Int): ToDo

    @DELETE("todos/{index}/")
    suspend fun deleteToDo(@Path("index") index: Int)
}

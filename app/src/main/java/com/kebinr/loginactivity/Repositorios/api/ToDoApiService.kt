package com.kebinr.loginactivity.Repositorios.api
import com.kebinr.loginactivity.Repositorios.api.DataAPi.ToDo
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ToDosApiService {

    private val ToDoApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(ToDosApi::class.java)


    suspend fun getToDo(index : Int): ToDo {
        return ToDoApi.getToDo(index)
    }

    suspend fun deleteToDo(index: Int){
        ToDoApi.deleteToDo(index)
    }

}
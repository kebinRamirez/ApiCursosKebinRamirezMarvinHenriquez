package com.kebinr.loginactivity.Repositorios.api.ApiProfessor

import com.kebinr.loginactivity.Data.ViewProfessorStudent
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ProfessorApi {


    @GET("{dbId}/professors")
    fun getProfessor(@Path("dbId") user: String, @Header("Authorization") header: String): Call<List<ViewProfessorStudent>>

    @GET("{dbId}/professors/{professorId}")
    fun viewProfessor(@Path("dbId") user: String, @Header("Authorization")header: String,@Path("professorId") courseId: String): Call<ViewProfessorStudent>
}
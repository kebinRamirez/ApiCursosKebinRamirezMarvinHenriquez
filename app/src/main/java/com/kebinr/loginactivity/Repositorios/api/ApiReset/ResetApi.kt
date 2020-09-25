package com.kebinr.loginactivity.Repositorios.api.ApiReset

import com.kebinr.loginactivity.Data.Course
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path

interface ResetApi {
    @GET("{dbId}/restart")
    fun restart(@Path("dbId") user: String, @Header("Authorization") header: String): Call<String>
}
package com.kebinr.loginactivity.Repositorios.api.ApiStudent

import com.kebinr.loginactivity.Data.Student
import com.kebinr.loginactivity.Data.ViewProfessorStudent
import okhttp3.ResponseBody
import retrofit2.Call
import retrofit2.http.*

interface StudentApi {

    @GET("{dbId}/students")
    fun getStudents(@Path("dbId") user: String, @Header("Authorization") header: String): Call<List<Student>>

    @FormUrlEncoded
    @POST("{dbId}/students")
    fun addStudent(@Path("dbId") user: String, @Header("Authorization") header: String, @Field("courseId") courseID: String): Call<ResponseBody>

    @GET("{dbId}/students/{studentId}")
    fun viewStudent(@Path("dbId") user: String, @Header("Authorization")header: String,@Path("studentId") courseId: String): Call<ViewProfessorStudent>

}
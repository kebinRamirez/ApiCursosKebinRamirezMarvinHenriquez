package com.kebinr.loginactivity.Repositorios.api.ApiCourse

import com.kebinr.loginactivity.Data.Course
import com.kebinr.loginactivity.Data.viewCourse
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.Path
import java.sql.RowId

interface CourseApi {

    @GET("{dbId}/courses")
    fun getCourses(@Path("dbId") user: String, @Header("Authorization") header: String): Call<List<Course>>

    @POST("{dbId}/courses")
    fun addCourse(@Path("dbId") user: String, @Header("Authorization") header: String): Call<Course>

    @GET("{dbId}/courses/{courseID}")
    fun viewCourse(@Path("dbId") user: String, @Header("Authorization")header: String,@Path("courseID") courseId: String): Call<viewCourse>

}
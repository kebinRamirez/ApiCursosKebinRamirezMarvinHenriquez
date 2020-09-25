package com.kebinr.loginactivity.Repositorios.api.ApiStudent

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kebinr.loginactivity.Data.Student
import com.kebinr.loginactivity.Data.ViewProfessorStudent
import okhttp3.OkHttpClient
import okhttp3.ResponseBody
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class StudentApiService {

    companion object{

        val theResponse = MutableLiveData<List<Student>>()
        val theResponse2 = MutableLiveData<ViewProfessorStudent>()
        var students = mutableListOf<Student>()

        fun getRestEngine(): StudentApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(StudentApi::class.java)
        }
    }

    fun getStudentData() =
        theResponse
    fun getViewStudent() =
        theResponse2


    fun getStudents(user: String, token: String){

        val auth = "Bearer "+token
        getRestEngine()
            .getStudents(user,auth).enqueue(object: Callback<List<Student>> {
            override fun onResponse(call: Call<List<Student>>, response: Response<List<Student>>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //theResponse.value = response.body()
                        theResponse.postValue(response.body())
                        students.clear()
                        val t = response.body() as List<Student>
                        students.addAll(t)
                        theResponse.postValue(
                            students
                        )
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<List<Student>>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })

    }

    fun addStudent(user: String, token: String, courseID : String) {

        Log.d("MyOut", "addCourse with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine()
            .addStudent(user,auth, courseID).enqueue(object:
            Callback<ResponseBody> {
            override fun onResponse(call: Call<ResponseBody>, response: Response<ResponseBody>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful " + response.body()?.string())
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    // Log.d("MyOut", "NOK isNotSuccessful " + response.errorBody()?.string())
                }

            }

            override fun onFailure(call: Call<ResponseBody>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

    fun getStudentInfo(user: String, token: String, studentId : String){
        val auth = "Bearer "+token
        getRestEngine()
            .viewStudent(user,auth,studentId).enqueue(object: Callback<ViewProfessorStudent> {
            override fun onResponse(call: Call<ViewProfessorStudent>, response: Response<ViewProfessorStudent>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //theResponse.value = response.body()
                        theResponse2.postValue(response.body())
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<ViewProfessorStudent>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })
    }

}
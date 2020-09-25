package com.kebinr.loginactivity.Repositorios.api.ApiReset

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kebinr.loginactivity.Data.Course
import com.kebinr.loginactivity.Data.viewCourse
import com.kebinr.loginactivity.Repositorios.api.ApiCourse.CourseApi
import com.kebinr.loginactivity.Repositorios.api.ApiCourse.CourseApiService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ResetApiService {
    companion object{
        val theResponse = MutableLiveData<String>()

        fun getRestEngine(): ResetApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ResetApi::class.java)

        }
    }

    fun restart(user: String, token: String){
        //Log.d("MyOut", "getCourses with token  <" + token+">")
        val auth = "Bearer "+token
        ResetApiService.getRestEngine()
            .restart(user,auth).enqueue(object: Callback<String> {
                override fun onResponse(call: Call<String>, response: Response<String>) {
                    if (response.isSuccessful) {
                        Log.d("MyOut", "OK isSuccessful ")
                        val loginResponse = response.body()
                        if (loginResponse != null) {
                            //theResponse.value = response.body()
                            theResponse.postValue(response.body())

                            val t = response.body() as String

                            theResponse.postValue(
                                t
                            )
                        }
                    } else {
                        Log.d("MyOut", "NOK  "+response.code() )
                        Log.d("MyOut", "NOK  "+response.toString() )
                        Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                    }
                }

                override fun onFailure(call: Call<String>, t: Throwable) {
                    Log.d("MyOut","Failure "+t.message)
                }

            })

    }
}
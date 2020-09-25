package com.kebinr.loginactivity.Repositorios.api.ApiProfessor

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.kebinr.loginactivity.Data.ViewProfessorStudent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ProfessorApiService {
    companion object{

        val theResponse = MutableLiveData<List<ViewProfessorStudent>>()
        val theResponse2 = MutableLiveData<ViewProfessorStudent>()
        var professors = mutableListOf<ViewProfessorStudent>()

        fun getRestEngine(): ProfessorApi {
            val interceptor = HttpLoggingInterceptor()
            interceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            return Retrofit.Builder()
                .baseUrl("https://movil-api.herokuapp.com/")

                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build()
                .create(ProfessorApi::class.java)

        }
    }

    fun getCourseData() =
        theResponse
    fun getViewProfesor() =
        theResponse2


    fun getProffesor(user: String, token: String){

        //Log.d("MyOut", "getCourses with token  <" + token+">")
        val auth = "Bearer "+token
        getRestEngine()
            .getProfessor(user,auth).enqueue(object: Callback<List<ViewProfessorStudent>> {
            override fun onResponse(call: Call<List<ViewProfessorStudent>>, response: Response<List<ViewProfessorStudent>>) {
                if (response.isSuccessful) {
                    Log.d("MyOut", "OK isSuccessful ")
                    val loginResponse = response.body()
                    if (loginResponse != null) {
                        //theResponse.value = response.body()
                        theResponse.postValue(response.body())
                        professors.clear()
                        val t = response.body() as List<ViewProfessorStudent>
                        professors.addAll(t)
                        theResponse.postValue(
                            professors
                        )
                    }
                } else {
                    Log.d("MyOut", "NOK  "+response.code() )
                    Log.d("MyOut", "NOK  "+response.toString() )
                    Log.d("MyOut", "NOK  "+response.errorBody().toString() )
                }
            }

            override fun onFailure(call: Call<List<ViewProfessorStudent>>, t: Throwable) {
                Log.d("MyOut","Failure "+t.message)
            }

        })

    }


    fun getProfessorInfo(user: String, token: String, professorId : String){
        val auth = "Bearer "+token
        getRestEngine()
            .viewProfessor(user,auth,professorId).enqueue(object: Callback<ViewProfessorStudent> {
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
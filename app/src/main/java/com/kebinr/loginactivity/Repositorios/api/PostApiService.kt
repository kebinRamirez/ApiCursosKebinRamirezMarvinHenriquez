package com.kebinr.loginactivity.Repositorios.api
import com.kebinr.loginactivity.Repositorios.api.DataAPi.Post
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class PostsApiService {

    private val postsApi = Retrofit.Builder()
        .baseUrl("https://jsonplaceholder.typicode.com/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create(PostsApi::class.java)


    suspend fun getPost(index : Int): Post {
        return postsApi.getPost(index)
    }

    suspend fun deletePost(index: Int){
        postsApi.deletePost(index)
    }

}
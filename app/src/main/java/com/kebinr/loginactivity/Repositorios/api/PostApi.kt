package com.kebinr.loginactivity.Repositorios.api

import com.kebinr.loginactivity.Repositorios.api.DataAPi.Post
import retrofit2.http.*

interface PostsApi {

    @GET("posts/{index}/")
    suspend fun getPost(@Path("index") index: Int): Post

    @DELETE("posts/{index}/")
    suspend fun deletePost(@Path("index") index: Int)
}

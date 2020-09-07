package com.kebinr.loginactivity.Repositorios

import com.kebinr.loginactivity.Repositorios.api.PostsApiService

class PostInfo {
    private val apiService = PostsApiService()

    suspend fun getPost(index : Int) = apiService.getPost(index)

    suspend fun deletePost(index : Int) = apiService.deletePost(index)
}
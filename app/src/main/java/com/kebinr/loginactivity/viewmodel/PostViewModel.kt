package com.kebinr.loginactivity.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kebinr.loginactivity.Repositorios.PostInfo
import com.kebinr.loginactivity.Repositorios.api.DataAPi.Post
import kotlinx.coroutines.launch

class PostViewModel: ViewModel() {

    private val repository = PostInfo()
    val posts = mutableListOf<Post>()
    val postsLiveData = MutableLiveData<List<Post>>()

    init {
        getPost()
    }

    fun getPost() {
        viewModelScope.launch {
            val post = repository.getPost(posts.size+1)
            posts.add(post)
            postsLiveData.postValue(posts)
        }
    }

    fun deletePost(index : Int, post : Post){
        viewModelScope.launch {
            repository.deletePost(index)
            posts.remove(post)
            postsLiveData.postValue(posts)
        }
    }

}
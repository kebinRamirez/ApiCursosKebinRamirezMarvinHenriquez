package com.kebinr.loginactivity.viewmodel


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.kebinr.loginactivity.Repositorios.ToDotInfo
import com.kebinr.loginactivity.Repositorios.api.DataAPi.ToDo
import kotlinx.coroutines.launch

class ToDoViewModel: ViewModel() {

    private val repository = ToDotInfo()
    val todos = mutableListOf<ToDo>()
    val postsLiveData = MutableLiveData<List<ToDo>>()

    init {
        getToDo()
    }

    fun getToDo() {
        viewModelScope.launch {
            val post = repository.getToDo(todos.size+1)
            todos.add(post)
            postsLiveData.postValue(todos)
        }
    }

    fun deleteToDo(index : Int, toDo : ToDo){
        viewModelScope.launch {
            repository.deleteToDo(index)
            todos.remove(toDo)
            postsLiveData.postValue(todos)
        }
    }

}
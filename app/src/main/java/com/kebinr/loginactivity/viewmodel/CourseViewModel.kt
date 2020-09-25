package com.kebinr.loginactivity.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Data.Course
import com.kebinr.loginactivity.Repositorios.CourseInfo

class CourseViewModel : ViewModel() {

    private val repository = CourseInfo()
    fun getCourses(user: String, token: String) = repository.getCourses(user, token)

    fun addCourse(user: String, token: String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
        repository.addCourse(user, token)
    }

    fun getCourseData() = repository.getCourseData()

    fun getCourseInfo(User: String, token: String, courseID : String) = repository.getCourseInfo(User, token, courseID)

    fun getviewCourse() =repository.getviewCourse()

}
package com.kebinr.loginactivity.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Repositorios.StudentInfo

class StudentViewModel : ViewModel() {
    private val repository = StudentInfo()
    fun getStudents(user: String, token: String) = repository.getStudents(user, token)

    fun addStudent(user: String, token: String, courseID : String)  {
        Log.d("MyOut", "CourseViewModel addCourses with token  <" + token+">")
        repository.addStudent(user, token, courseID)
    }

    fun getStudentData() = repository.getStudentData()

    fun getStudentInfo(User: String, token: String, studentID : String) = repository.getStudentInfo(User, token, studentID)

    fun getviewStudent() =repository.getviewStudent()
}
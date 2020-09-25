package com.kebinr.loginactivity.Repositorios

import com.kebinr.loginactivity.Repositorios.api.ApiStudent.StudentApiService

class StudentInfo {

    private val service =
        StudentApiService()

    fun getStudents(user: String, token: String) = service.getStudents(user,token)

    fun addStudent(user: String, token: String, courseID: String) = service.addStudent(user,token, courseID)

    fun getStudentData() = service.getStudentData()

    fun getStudentInfo(User: String, token: String, studentId : String) = service.getStudentInfo(User, token, studentId)

    fun getviewStudent() =service.getViewStudent()

}
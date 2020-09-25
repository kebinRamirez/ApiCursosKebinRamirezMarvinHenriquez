package com.kebinr.loginactivity.Repositorios

import com.kebinr.loginactivity.Repositorios.api.ApiCourse.CourseApiService

class CourseInfo {

    private val service =
        CourseApiService()

    fun getCourses(user: String, token: String) = service.getCourses(user,token)

    fun addCourse(user: String, token: String) = service.addCourse(user,token)

    fun getCourseData() = service.getCourseData()

    fun getCourseInfo(User: String, token: String, courseID : String) = service.getCourseInfo(User, token, courseID)

    fun getviewCourse() =service.getViewCourse()
}
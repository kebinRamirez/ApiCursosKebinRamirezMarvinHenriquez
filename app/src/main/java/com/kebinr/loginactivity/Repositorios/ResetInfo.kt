package com.kebinr.loginactivity.Repositorios

import com.kebinr.loginactivity.Repositorios.api.ApiCourse.CourseApiService
import com.kebinr.loginactivity.Repositorios.api.ApiReset.ResetApiService

class ResetInfo {

    private val service =
        ResetApiService()

    fun restart(user: String, token: String) = service.restart(user,token)
}
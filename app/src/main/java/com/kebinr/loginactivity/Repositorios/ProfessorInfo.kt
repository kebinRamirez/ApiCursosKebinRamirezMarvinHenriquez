package com.kebinr.loginactivity.Repositorios

import com.kebinr.loginactivity.Repositorios.api.ApiProfessor.ProfessorApiService

class ProfessorInfo {

    private val service =
        ProfessorApiService()

    fun getProfessor(user: String, token: String) = service.getProffesor(user,token)

    fun getProfessorData() = service.getCourseData()

    fun getProfessorInfo(User: String, token: String, courseID : String) = service.getProfessorInfo(User, token, courseID)

    fun getviewProfesor() =service.getViewProfesor()
}
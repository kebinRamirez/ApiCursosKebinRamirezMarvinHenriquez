package com.kebinr.loginactivity.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Repositorios.CourseInfo
import com.kebinr.loginactivity.Repositorios.ProfessorInfo

class ProfessorViewModel : ViewModel() {

    private val repository = ProfessorInfo()

    fun getProfessor(user: String, token: String) = repository.getProfessor(user, token)


    fun getProfessorData() = repository.getProfessorData()

    fun getProfessorInfo(User: String, token: String, professorID : String) = repository.getProfessorInfo(User, token, professorID)

    fun getviewProfessor() =repository.getviewProfesor()
}
package com.kebinr.loginactivity.viewmodel

import androidx.lifecycle.ViewModel
import com.kebinr.loginactivity.Repositorios.CourseInfo
import com.kebinr.loginactivity.Repositorios.ResetInfo

class ResetViewModel : ViewModel() {
    private val repository = ResetInfo()
    fun restart(user: String, token: String) = repository.restart(user, token)
}
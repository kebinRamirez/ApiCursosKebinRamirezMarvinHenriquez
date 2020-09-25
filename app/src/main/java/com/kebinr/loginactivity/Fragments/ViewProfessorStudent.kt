package com.kebinr.loginactivity.Fragments

import android.media.tv.TvView
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import com.kebinr.loginactivity.viewmodel.ProfessorViewModel
import com.kebinr.loginactivity.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_view_professor.*


class ViewProfessorStudent : Fragment() {
    val loginViewModel: LoginViewModel by activityViewModels()
    val professorViewModel : ProfessorViewModel by activityViewModels()
    val studentViewModel : StudentViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_view_professor, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var usuario :String = loginViewModel.getUser()!!
        var separacion = usuario.split(";")
        var user : String = separacion[0]
        var token : String = separacion [1]
        val info: String? =requireArguments()!!.getString("profesorid")
        val secuencia = info!!.split("/")
        val id = secuencia[0]
        val Tipo = secuencia[1]
        if (Tipo.equals("profesor")){
            professorViewModel.getProfessorInfo(user,token,id!!)
            professorViewModel.getviewProfessor().observe(getViewLifecycleOwner(), Observer {
                NombreP.text = "Profesor: "+it.name
                courseid.text = "Id Curso:" +it.course_id
                username.text = "Nombre Usuario: " + it.username
                email.text = "Email: " + it.email
                phone.text= "Telefono: "+ it.phone
                city.text = "Ciudad: " + it.city
                country.text = "pais: " + it.country
                birthday.text = "Cumpleaños: " + it.birthday
            })
        }else{
            studentViewModel.getStudentInfo(user,token,id!!)
            studentViewModel.getviewStudent().observe(getViewLifecycleOwner(), Observer{
                NombreP.text = "Estudiante: "+it.name
                courseid.text = "Id Curso:" +it.course_id
                username.text = "Nombre Usuario: " + it.username
                email.text = "Email: " + it.email
                phone.text= "Telefono: "+ it.phone
                city.text = "Ciudad: " + it.city
                country.text = "pais: " + it.country
                birthday.text = "Cumpleaños: " + it.birthday
            })

        }

    }

}
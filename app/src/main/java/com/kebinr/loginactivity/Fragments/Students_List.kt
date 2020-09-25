package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kebinr.loginactivity.Data.Student
import com.kebinr.loginactivity.Data.viewCourse
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.CourseViewModel
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import com.kebinr.loginactivity.viewmodel.StudentViewModel
import kotlinx.android.synthetic.main.fragment_logged.view.*
import kotlinx.android.synthetic.main.fragment_students__list.*
import java.text.FieldPosition

class Students_List : Fragment(), StudentAdapter.onListIteration {

    val loginViewModel: LoginViewModel by activityViewModels()
    val studentViewModel : StudentViewModel by activityViewModels()
    val courseViewModel : CourseViewModel by activityViewModels()
    private var adapter = StudentAdapter(ArrayList(),this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_students__list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        requireView().posts_recycler.adapter = adapter
        requireView().posts_recycler.layoutManager = LinearLayoutManager(requireContext())
        val id: String? =requireArguments()!!.getString("courseid")

        var usuario :String = loginViewModel.getUser()!!
        var separacion = usuario.split(";")
        var username : String = separacion[0]
        var token : String = separacion [1]
        courseViewModel.getCourseInfo(username,token,id!!)
        courseViewModel.getviewCourse().observe(getViewLifecycleOwner(), Observer {
            textView.text = it.name
            adapter.Students.clear()
            adapter.Students.add(it.professor)
            adapter.Students.addAll(it.students)
            adapter.notifyDataSetChanged()
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            studentViewModel.addStudent(username,token,id!!)
            courseViewModel.getCourseInfo(username,token,id!!)
            courseViewModel.getviewCourse().observe(getViewLifecycleOwner(), Observer {
                textView.text = it.name
                adapter.Students.clear()
                adapter.Students.add(it.professor)
                adapter.Students.addAll(it.students)
                adapter.notifyDataSetChanged()
            })
        }

        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == false) {
                navController.navigate(R.id.login)
            }
        })
        view.findViewById<Button>(R.id.button3).setOnClickListener {
            MaterialAlertDialogBuilder(requireContext())
                .setTitle("Inicio")
                .setMessage("Está seguro que desea cerrar sesion?")
                .setPositiveButton("Aceptar") { dialog, which ->
                    // Respond to positive button press
                    loginViewModel.setLogged(false)
                    loginViewModel.setUserInfo("-1","-1")
                }
                .show()

        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Students_List().apply {
                arguments = Bundle().apply {
                }
            }
    }

    override fun onListItemInteration(item: Student?) {
        TODO("Not yet implemented")
    }

    override fun onListButtonInteraction(item: Student?, position: Int) {
        val navController = findNavController()
        if (position == 0){
            var info : String = item!!.id + "/profesor"
            val bundle = bundleOf("profesorid" to info)
            navController.navigate(R.id.action_students_List_to_viewProfessor,bundle)
        }else{
            var info : String = item!!.id + "/estudiante"
            val bundle = bundleOf("profesorid" to info)
            navController.navigate(R.id.action_students_List_to_viewProfessor,bundle)
        }
    }
}
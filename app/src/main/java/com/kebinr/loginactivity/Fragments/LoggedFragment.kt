package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kebinr.loginactivity.Data.Course
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.CourseViewModel
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import com.kebinr.loginactivity.viewmodel.ResetViewModel
import kotlinx.android.synthetic.main.fragment_logged.view.*
import okhttp3.internal.notify
import okhttp3.internal.notifyAll

class LoggedFragment : Fragment(), CourseAdapter.onListIteration {
    val loginViewModel: LoginViewModel by activityViewModels()
    val courseViewModel : CourseViewModel by activityViewModels()
    val resetViewModel : ResetViewModel by activityViewModels()

    private val adapter = CourseAdapter(ArrayList(), this)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_logged, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()

        requireView().posts_recycler.adapter = adapter
        requireView().posts_recycler.layoutManager = LinearLayoutManager(requireContext())

        var usuario :String = loginViewModel.getUser()!!
        var separacion = usuario.split(";")
        var username : String = separacion[0]
        var token : String = separacion [1]

        view.findViewById<Button>(R.id.btnReset).setOnClickListener{
            resetViewModel.restart(username,token)
            courseViewModel.getCourseData().observe(getViewLifecycleOwner(), Observer {
                println("TIENE TANTOS CURSOS: "+it.size)
                adapter.Courses.clear()
                adapter.Courses.addAll(it)
                adapter.notifyDataSetChanged()
                navController!!.navigate(R.id.loggedFragment)
            })

        }
        courseViewModel.getCourses(username,token)
        courseViewModel.getCourseData().observe(getViewLifecycleOwner(), Observer {
            println("TIENE TANTOS CURSOS: "+it.size)
            adapter.Courses.clear()
            adapter.Courses.addAll(it)
            adapter.notifyDataSetChanged()
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            courseViewModel.addCourse(username,token)
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
            LoggedFragment().apply {
            }
    }

    override fun onListItemInteration(item: Course?) {
        //escribe cuando interactuan con la vista del item
        println("item sin accion (Seleccionado)")

    }

    override fun onListButtonInteraction(item: Course?) {
        val navController = findNavController()
        val bundle = bundleOf("courseid" to item!!.id)
        navController.navigate(R.id.action_loggedFragment_to_students_List,bundle)
    }


}
package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.Repositorios.api.DataAPi.ToDo
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import com.kebinr.loginactivity.viewmodel.ToDoViewModel
import kotlinx.android.synthetic.main.fragment_logged.view.*

class LoggedFragment : Fragment(), ToDosAdapter.onListIteration {
    val loginViewModel: LoginViewModel by activityViewModels()
    val toDoViewModel: ToDoViewModel by activityViewModels()
    private val adapter = ToDosAdapter(ArrayList(), this)
    lateinit var toDos : List<ToDo>
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

        toDoViewModel.postsLiveData.observe(getViewLifecycleOwner(), Observer {
            adapter.toDos.clear()
            adapter.toDos.addAll(it)
            adapter.notifyDataSetChanged()
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            toDoViewModel.getToDo()
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

    override fun onListItemInteration(item: ToDo?) {
        //escribe cuando interactuan con la vista del item
        println("item sin accion (Seleccionado)")
    }

    override fun onListButtonInteraction(item: ToDo?) {
        adapter.toDos.remove(item)
        val posicion =adapter.toDos.indexOf(item)
        toDoViewModel.deleteToDo(posicion, item!!)
        adapter.notifyItemRemoved(posicion)
    }
}
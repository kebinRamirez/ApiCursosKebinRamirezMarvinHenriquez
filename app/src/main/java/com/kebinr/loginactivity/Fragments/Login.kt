package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kebinr.loginactivity.Data.User
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import com.kebinr.loginactivity.viewmodel.UsersInfoViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class Login : Fragment() {
    private var userList = mutableListOf<User>()
    lateinit var navController: NavController
    val loginViewModel: LoginViewModel by activityViewModels()
    val myViewModel : UsersInfoViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //navController = Navigation.findNavController(view)
        //view.findViewById<Button>(R.id.button).setOnClickListener(this)
        //view.findViewById<Button>(R.id.button2).setOnClickListener(this)
        val navController = findNavController()
        loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
            if (logged == true){
                navController.navigate(R.id.loggedFragment)
            }
        })
        var ingreso = false
        var usu = false
        view.findViewById<Button>(R.id.button).setOnClickListener(){
            myViewModel.getUsers().observe(viewLifecycleOwner, Observer { users->
                run {
                    val some= ""+users.size
                    userList = users as MutableList<User>
                    users.forEach{ user ->
                        //verificar que el usuario exista
                        if (user.correo.equals(editTextTextEmailAddress.text.toString())){
                            usu=true
                            if (user.contraseña.equals(editTextTextPassword.text.toString())){
                                //usuario registrado inicio de sesion
                                ingreso = true
                                loginViewModel.setLogged(true)
                            }else{
                                //usuario Registrado, Contraseña incorrecta
                                println("Contraseña incorrecta")
                                MaterialAlertDialogBuilder(requireContext())
                                    .setTitle("Login")
                                    .setMessage("Contraseña Incorrecta")
                                    .setPositiveButton("Aceptar") { dialog, which ->
                                        // Respond to positive button press
                                    }
                                    .show()
                            }
                        }
                    }
                }
            })
            if (ingreso==false && editTextTextEmailAddress.text.toString().equals("")){
                //campo vacio
                MaterialAlertDialogBuilder(requireContext())
                    .setTitle("Login")
                    .setMessage("Ingrese Usuario y contraseña")
                    .setPositiveButton("Aceptar") { dialog, which ->
                        // Respond to positive button press
                    }
                    .show()
            }else{
                if (ingreso==false && usu==false){
                    //usuario no registrado
                    MaterialAlertDialogBuilder(requireContext())
                        .setTitle("Login")
                        .setMessage("No se encontro el usuario")
                        .setPositiveButton("Aceptar") { dialog, which ->
                            // Respond to positive button press
                        }
                        .show()
                }
            }
        }
        view.findViewById<Button>(R.id.button2).setOnClickListener(){
            navController!!.navigate(R.id.action_login_to_registroFragment)
        }
    }
    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            Login().apply {

            }
    }


}
package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_registro.*

class RegistroFragment : Fragment(){
    lateinit var navController: NavController
    val loginViewModel: LoginViewModel by activityViewModels()
    var theToken : String = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_registro, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        navController = Navigation.findNavController(view)
        view.findViewById<Button>(R.id.button2).setOnClickListener(){
            val email : String =  editTextTextEmailAddress.text.toString()
            val clave : String = editTextTextPassword2.text.toString()
            val usuario : String = Username.text.toString()

            if (Username.text.toString()!="" && editTextTextPassword2.text.toString()!= ""&& editTextTextEmailAddress.text.toString()!=""){
                //bien, las contraseñas coinciden
                loginViewModel.signUp(email,clave,usuario).observe(getViewLifecycleOwner(), Observer { user ->
                    Log.d("MyOut", "Fragment  signUp " + user + " error " + user.error)
                    theToken = user.token

                    Toast.makeText(context, "Token " + user.token+" Creado", Toast.LENGTH_LONG).show()
                    println("EL ERROR ES: " + user.error)
                    println("EL TAMAÑO DEL ERROR ES: " + user.error.length)
                    Username.setText("")
                    editTextTextPassword2.setText("")
                    editTextTextEmailAddress.setText("")
                    if (user.error.equals("")){
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Registro")
                            .setMessage("Registrado Correctamente")
                            .setPositiveButton("Aceptar") { dialog, which ->
                                // Respond to positive button press
                                navController!!.navigate(R.id.action_registroFragment_to_login)
                            }
                            .show()
                    }else{
                        MaterialAlertDialogBuilder(requireContext())
                            .setTitle("Registro")
                            .setMessage("Algo raro pasó")
                            .setPositiveButton("Aceptar") { dialog, which ->
                                // Respond to positive button press
                            }
                            .show()
                    }

                })

            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) =
            RegistroFragment().apply {
            }
    }
}
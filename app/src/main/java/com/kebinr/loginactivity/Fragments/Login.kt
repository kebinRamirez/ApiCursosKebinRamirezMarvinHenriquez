package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import com.kebinr.loginactivity.Data.User
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

class Login : Fragment() {
    var userTest: String =  ""
    var passTest: String = ""
    var theToken : String = ""
    var useTest : String = ""
    val loginViewModel: LoginViewModel by activityViewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        loginViewModel.setLogged(false)
        loginViewModel.setUserInfo("-1","-1")
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val navController = findNavController()
        view.findViewById<Button>(R.id.button).setOnClickListener(){
            userTest = editTextTextEmailAddress.text.toString()
            passTest = editTextTextPassword.text.toString()
            useTest = Usuario.text.toString()
            loginViewModel.getLogged().observe(viewLifecycleOwner, Observer { logged ->
                if (logged == true){
                    navController.navigate(R.id.loggedFragment)
                }
            })
            loginViewModel.signIn(userTest,passTest,useTest).observe(getViewLifecycleOwner(), Observer { user ->

                //Log.d("MyOut", "Fragment  signIn " + user + " error " + user.error)
                theToken = user.token
                if (user.token != "") {
                    Toast.makeText(context, "Token " + user.token+"el correo "+ user.email+" existe", Toast.LENGTH_LONG).show()
                    loginViewModel.setUserInfo(user.username,user.token)
                    println("LOS DATOS DEL USUARIO A VER SON: " + loginViewModel.getUser())
                    loginViewModel.setLogged(true)
                } else {
                    Toast.makeText(context, "Token failure " + user.error, Toast.LENGTH_LONG)
                        .show()
                }
            })
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
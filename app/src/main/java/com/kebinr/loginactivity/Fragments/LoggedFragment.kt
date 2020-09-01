package com.kebinr.loginactivity.Fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.viewmodel.LoginViewModel

class LoggedFragment : Fragment() {
    val loginViewModel: LoginViewModel by activityViewModels()
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
}
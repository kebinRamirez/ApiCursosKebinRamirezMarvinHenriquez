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
import com.kebinr.loginactivity.Repositorios.api.DataAPi.Post
import com.kebinr.loginactivity.viewmodel.LoginViewModel
import com.kebinr.loginactivity.viewmodel.PostViewModel
import kotlinx.android.synthetic.main.fragment_logged.view.*

class LoggedFragment : Fragment(), PostsAdapter.onListIteration {
    val loginViewModel: LoginViewModel by activityViewModels()
    val postViewModel: PostViewModel by activityViewModels()
    private val adapter = PostsAdapter(ArrayList(), this)
    lateinit var posts : List<Post>
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

        postViewModel.postsLiveData.observe(getViewLifecycleOwner(), Observer {
            adapter.posts.clear()
            adapter.posts.addAll(it)
            adapter.notifyDataSetChanged()
        })

        view.findViewById<FloatingActionButton>(R.id.floatingActionButton).setOnClickListener {
            postViewModel.getPost()
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

    override fun onListItemInteration(item: Post?) {
        //escribe cuando interactuan con la vista del item
        println("item sin accion (Seleccionado)")
    }

    override fun onListButtonInteraction(item: Post?) {
        adapter.posts.remove(item)
        val posicion =adapter.posts.indexOf(item)
        postViewModel.deletePost(posicion, item!!)
        adapter.notifyItemRemoved(posicion)
    }
}
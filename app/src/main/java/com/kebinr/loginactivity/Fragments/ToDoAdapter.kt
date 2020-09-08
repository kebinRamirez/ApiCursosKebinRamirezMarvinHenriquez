package com.kebinr.loginactivity.Fragments

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.Repositorios.api.DataAPi.ToDo
import kotlinx.android.synthetic.main.list_item_to.view.*

class ToDosAdapter(val toDos: ArrayList<ToDo>,
                   private val dListener: onListIteration ): RecyclerView.Adapter<ToDosAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_to, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return toDos.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(toDos[position])
        val item =toDos[position]
        holder.button.setOnClickListener{
            dListener?.onListItemInteration(item)
        }
        holder.button.setOnClickListener{
            dListener?.onListButtonInteraction(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(toDo: ToDo) {
            itemView.title.text = toDo.title
            if (toDo.completed){
                itemView.CartaColor.setBackgroundColor(Color.parseColor("#3C682B"))
            }else{
                itemView.CartaColor.setBackgroundColor(Color.parseColor("#721F1F"))
            }

        }
        val button : Button = itemView.button5
    }
    interface onListIteration{
        fun onListItemInteration(item : ToDo?)
        fun onListButtonInteraction(item : ToDo?)
    }
}



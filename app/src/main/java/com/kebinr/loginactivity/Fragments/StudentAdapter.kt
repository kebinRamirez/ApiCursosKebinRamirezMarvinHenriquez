package com.kebinr.loginactivity.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.kebinr.loginactivity.Data.Student
import com.kebinr.loginactivity.R
import kotlinx.android.synthetic.main.list_item_students.view.*

class StudentAdapter(val Students: ArrayList<Student>,private val llListener: onListIteration ): RecyclerView.Adapter<StudentAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_students, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Students.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Students[position],position)
        val item =Students[position]
        holder.button.setOnClickListener{
            llListener?.onListItemInteration(item)
        }
        holder.button.setOnClickListener{
            llListener?.onListButtonInteraction(item,position)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(student: Student, position: Int) {
            if (position==0){
                itemView.nombre.text = student.name
                itemView.ID.text = "id: " + student.id
                itemView.email.text ="Email: " + student.email
                itemView.username.text = "Nombre Usuario: " + student.username
                itemView.textView4.text = "Profesor"
            }else{
                itemView.nombre.text = student.name
                itemView.ID.text = "id: " + student.id
                itemView.email.text ="Email: " + student.email
                itemView.username.text = "Nombre Usuario: " + student.username
                itemView.textView4.text = "Estudiante"
            }
        }
        val button : Button = itemView.button5
    }
    interface onListIteration{
        fun onListItemInteration(item : Student?)
        fun onListButtonInteraction(item : Student?, position: Int)
    }
}
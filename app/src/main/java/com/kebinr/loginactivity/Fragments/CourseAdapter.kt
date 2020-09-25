package com.kebinr.loginactivity.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.kebinr.loginactivity.Data.Course
import com.kebinr.loginactivity.R
import kotlinx.android.synthetic.main.list_item_course.view.*

class CourseAdapter(val Courses: ArrayList<Course>,
                   private val dListener: onListIteration ): RecyclerView.Adapter<CourseAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_course, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return Courses.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(Courses[position])
        val item =Courses[position]
        holder.button.setOnClickListener{
            dListener?.onListItemInteration(item)
        }
        holder.button.setOnClickListener{
            dListener?.onListButtonInteraction(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(course: Course) {
            itemView.nombre.text = course.name
            itemView.ID.text = "id: " + course.id
            itemView.email.text ="Profesor: " + course.professor
            itemView.username.text = "Estudiantes: " + course.students
        }
        val button : Button = itemView.button5
    }
    interface onListIteration{
        fun onListItemInteration(item : Course?)
        fun onListButtonInteraction(item : Course?)
    }
}



package com.kebinr.loginactivity.Fragments

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.kebinr.loginactivity.R
import com.kebinr.loginactivity.Repositorios.api.DataAPi.Post
import kotlinx.android.synthetic.main.list_item_post.view.*

class PostsAdapter(val posts: ArrayList<Post>,
                   private val dListener: onListIteration ): RecyclerView.Adapter<PostsAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.list_item_post, parent, false)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(posts[position])
        val item =posts[position]
        holder.button.setOnClickListener{
            dListener?.onListItemInteration(item)
        }
        holder.button.setOnClickListener{
            dListener?.onListButtonInteraction(item)
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(post: Post) {
            itemView.title.text = post.title
            itemView.body.text = post.body
        }
        val button : Button = itemView.button5
    }
    interface onListIteration{
        fun onListItemInteration(item : Post?)
        fun onListButtonInteraction(item : Post?)
    }
}
package com.example.daggerpractice.ui.main.posts

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.daggerpractice.R
import com.example.daggerpractice.models.Post
import kotlinx.android.synthetic.main.layout_post_list_item.view.*
import java.util.*

class PostsRecyclerAdapter :
    ListAdapter<Post, PostsRecyclerAdapter.PostHolder>(PostDiffCallback()) {

    private var posts: List<Post> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PostHolder {
        return PostHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.layout_post_list_item, parent, false)
        )
    }
    override fun onBindViewHolder(holder: PostsRecyclerAdapter.PostHolder, position: Int) {
        holder.bind(posts[position])
    }

    override fun getItemCount(): Int {
        return posts.size
    }

    fun setPosts(posts: List<Post>?) {
        if (posts != null) {
            this.posts = posts
            //notifyDataSetChanged()
        }
    }
    inner class PostHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val title: TextView = itemView.title
        fun bind(post: Post) {
            title.text = post.title
        }
    }
}

class PostDiffCallback : DiffUtil.ItemCallback<Post>() {
    override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.id == newItem.id
    }
    override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean {
        return oldItem.title == newItem.title
    }
}
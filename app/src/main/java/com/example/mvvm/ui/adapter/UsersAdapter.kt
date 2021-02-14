package com.example.mvvm.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.mvvm.R
import com.example.mvvm.data.models.User
import com.squareup.picasso.Picasso

class UsersAdapter(val data : List<User>) : RecyclerView.Adapter<UserViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = UserViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user,parent,false)
    )
    override fun getItemCount() = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {

          holder.bind(data[position])
    }


}

class UserViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {

      fun bind(item : User)  = with(itemView) {
            val username = findViewById<TextView>(R.id.username)
            username.text = item.login
            val imgView = findViewById<ImageView>(R.id.itemImgView)

            Picasso.with(context).load(item.avatarUrl).into(imgView)

      }
}
package com.example.eka_care_assignment.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.eka_care_assignment.R
import com.example.eka_care_assignment.model.User

class UserListAdapter : RecyclerView.Adapter<UserListAdapter.UserViewHolder>() {

    private var users = emptyList<User>()

    inner class UserViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val name: TextView = itemView.findViewById(R.id.tvName)
        val age: TextView = itemView.findViewById(R.id.tvAge)
        val dob: TextView = itemView.findViewById(R.id.tvDob)
        val address: TextView = itemView.findViewById(R.id.tvAddress)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return UserViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val current = users[position]
        holder.name.text = current.name
        holder.age.text = current.age.toString()
        holder.dob.text = current.dob
        holder.address.text = current.address
    }

    override fun getItemCount() = users.size

    fun setUsers(users: List<User>) {
        this.users = users
        notifyDataSetChanged()
    }
}

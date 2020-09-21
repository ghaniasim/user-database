package com.example.userdatabase.view

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabase.R
import com.example.userdatabase.model.User
import kotlinx.android.synthetic.main.item.view.*

class UserAdapter(private val items: List<User>?, private val context: Context): RecyclerView.Adapter<UserViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        UserViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item, parent, false)
        )

    override fun getItemCount() = items?.size ?: 0

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        holder.itemView.textView.text = items?.get(position).toString()
        holder.itemView.setOnClickListener {
            Log.d("DB", "Item clicked")

            val action =
                ListFragmentDirections.actionListFragmentToDetailFragment(items?.get(position)?.uid!!.toInt(), items?.get(position).toString())
            it.findNavController().navigate(action)
        }
    }
}
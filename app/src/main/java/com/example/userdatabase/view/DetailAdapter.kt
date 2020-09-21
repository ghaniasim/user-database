package com.example.userdatabase.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.userdatabase.R
import com.example.userdatabase.model.ContactInfo
import kotlinx.android.synthetic.main.item.view.*

class DetailAdapter(private val items: List<ContactInfo>): RecyclerView.Adapter<ContactDetailViewHolder>() {

    override fun getItemCount() = items?.size ?: 0

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)=
        ContactDetailViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        )

    override fun onBindViewHolder(holder: ContactDetailViewHolder, position: Int) {
        holder.itemView.textView.text = items?.get(position).toString()
    }
}
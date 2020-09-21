package com.example.userdatabase.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import android.widget.ArrayAdapter
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdatabase.R
import com.example.userdatabase.model.ContactInfo
import com.example.userdatabase.model.UserDB
import com.example.userdatabase.viewModel.UserModel
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class DetailFragment : Fragment(R.layout.fragment_detail) {

    private var uid: Int = 0
    private lateinit var details: String
    private val db by lazy { UserDB.get(requireActivity())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val args: DetailFragmentArgs by navArgs()

        tvUserDetails.text = args.name
        uid = args.uid
        details = args.name

        ArrayAdapter.createFromResource(
            requireActivity(),
            R.array.contact_types,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
        }
        spinner.setSelection(1)

        rvcontactDetails.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)

        val ump = ViewModelProviders.of(this).get(UserModel::class.java)
        ump.getContactDetails(uid.toLong()).observe(this) {
            rvcontactDetails.adapter = DetailAdapter(it.contacts!!)
        }

        btnSaveContact.setOnClickListener {
            saveContactDetails()
        }
    }

    private fun saveContactDetails() {
        val type =  spinner.selectedItem.toString()
        val detail = ptContactDetails.text.toString()

        if (!detail.isNullOrEmpty()) {
            GlobalScope.launch {
                db.contactDao().insert(ContactInfo(uid.toLong(), type, detail))
            }
        }
    }

}
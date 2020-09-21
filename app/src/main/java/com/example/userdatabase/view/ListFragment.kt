package com.example.userdatabase.view

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.observe
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.userdatabase.R
import com.example.userdatabase.viewModel.UserModel
import com.example.userdatabase.model.ContactInfo
import com.example.userdatabase.model.User
import com.example.userdatabase.model.UserDB
import kotlinx.android.synthetic.main.fragment_list.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ListFragment : Fragment(R.layout.fragment_list) {
    private val db by lazy { UserDB.get(requireActivity())}

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView.layoutManager =LinearLayoutManager(activity, LinearLayoutManager.VERTICAL ,false)
        val ump = ViewModelProviders.of(this).get(UserModel::class.java)

        ump.getUsers().observe(this) { it ->
            recyclerView.adapter =
                UserAdapter(it?.sortedBy {
                    it.lastname
                }, requireActivity())
        }

        btnSave.setOnClickListener {
            saveEntry()
        }
    }

    private fun saveEntry () {
        var firstName = ptFirstName.text.toString()
        var secondName = ptLastName.text.toString()
        var phoneNumber = ptPhone.text.toString()

        GlobalScope.launch {
            val id = db.userDao().insert(User(0, firstName, secondName))
            db.contactDao().insert(ContactInfo(id, "Phone", phoneNumber))
            withContext(Dispatchers.Main) {
                Log.d("DB", "User saved with id: $id")
            }
        }
    }
}
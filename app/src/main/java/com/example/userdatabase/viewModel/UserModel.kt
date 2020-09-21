package com.example.userdatabase.viewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.userdatabase.model.User
import com.example.userdatabase.model.UserDB

class UserModel (application: Application): AndroidViewModel(application) {
    private val users: LiveData<List<User>> =
        UserDB.get(getApplication()).userDao().getAll()

    fun getUsers() = users

    fun getContactDetails(uid: Long) =
        UserDB.get(getApplication()).userDao().getUserContacts(uid)
}
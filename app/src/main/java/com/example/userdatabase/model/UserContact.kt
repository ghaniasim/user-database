package com.example.userdatabase.model

import androidx.room.Embedded
import androidx.room.Relation

class UserContact {
    @Embedded
    var user: User? = null
    @Relation(parentColumn = "uid", entityColumn = "user")
    var contacts: List<ContactInfo>? = null
}
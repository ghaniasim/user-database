package com.example.userdatabase.model

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(foreignKeys = [(ForeignKey(entity = User::class, parentColumns = ["uid"], childColumns = ["user"]))])
data class ContactInfo(
    val user: Long,
    val type: String, //e.g. phone, email, fb, twitter,...
    @PrimaryKey
    val value: String){
    //constructor, getter and setter are implicit :)
    override fun toString() = "$type:  $value"
}
package com.example.userdatabase.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.fragment.NavHostFragment
import com.example.userdatabase.R

class MainActivity : AppCompatActivity() {
    val host = NavHostFragment.create(R.navigation.welcome_nav_graph)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportFragmentManager.beginTransaction().replace(R.id.flfragment, host).setPrimaryNavigationFragment(host).commit()
    }
}
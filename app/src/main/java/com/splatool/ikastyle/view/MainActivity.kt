package com.splatool.ikastyle.view

import androidx.navigation.ui.NavigationUI.setupWithNavController
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import androidx.navigation.fragment.NavHostFragment
import com.splatool.ikastyle.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val bottomNavView = findViewById<BottomNavigationView?>(R.id.bottom_navi)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.navi_fragment) as NavHostFragment
        val navController = navHostFragment.navController
        setupWithNavController(bottomNavView, navController)
    }
}
package com.example.daggerpractice.ui.main

import android.os.Bundle
import android.widget.Toast
import com.example.daggerpractice.BaseActivity
import com.example.daggerpractice.R

class MainActivity : BaseActivity() {
    private val TAG = "MainActivity"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Toast.makeText(this, "MainActivity", Toast.LENGTH_SHORT).show()
    }
}
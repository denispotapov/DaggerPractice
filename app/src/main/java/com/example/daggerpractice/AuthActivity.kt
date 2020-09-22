package com.example.daggerpractice

import android.os.Bundle
import android.util.Log
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

const val TAG = "AuthActivity"

class AuthActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var someString: String

    @JvmField
    @Inject
    var isAppNull: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        Log.d(TAG, "OnCreate + $someString")
        Log.d(TAG, "OnCreate: is app null? + $isAppNull")
    }
}
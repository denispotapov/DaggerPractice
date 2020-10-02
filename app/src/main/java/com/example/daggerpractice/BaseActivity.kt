package com.example.daggerpractice

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import com.example.daggerpractice.models.User
import com.example.daggerpractice.ui.auth.AuthActivity
import com.example.daggerpractice.ui.auth.AuthResource
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

abstract class BaseActivity : DaggerAppCompatActivity() {
    private val TAG = "BaseActivity"

    @Inject
    lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        subscribeObservers()
    }

    private fun subscribeObservers() {
        sessionManager.getAuthUser().observe(this, object : Observer<AuthResource<User>> {
            override fun onChanged(user: AuthResource<User>?) {
                if (user != null) {
                    when (user) {
                        is AuthResource.Loading -> {}
                        is AuthResource.Authenticated -> {
                            Log.d(TAG, "OnChanged: LOGIN SUCCESS ${user.data}")
                        }
                        is AuthResource.Error -> {
                            Log.d(TAG, "OnChanged: ${user.message}")
                        }
                        is AuthResource.Logout -> navLoginScreen()
                    }
                }
            }
        })
    }

    private fun navLoginScreen() {
        val intent = Intent(this, AuthActivity::class.java)
        startActivity(intent)
        finish()
    }
}
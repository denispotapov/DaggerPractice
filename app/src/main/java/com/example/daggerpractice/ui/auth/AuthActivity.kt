package com.example.daggerpractice.ui.auth

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.bumptech.glide.RequestManager
import com.example.daggerpractice.R
import com.example.daggerpractice.models.User
import com.example.daggerpractice.util.TAG
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_auth.*
import javax.inject.Inject

class AuthActivity : DaggerAppCompatActivity(), View.OnClickListener {

    @Inject
    lateinit var logo: Drawable

    @Inject
    lateinit var requestManager: RequestManager

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory

    lateinit var viewModel: AuthViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_auth)

        viewModel = ViewModelProvider(this, providerFactory).get(AuthViewModel::class.java)
        viewModel.log()
        viewModel.api()
        subscribeObservers()
        setLogo()
        login_button.setOnClickListener(this)

    }

    private fun subscribeObservers() {
        viewModel.observeUser().observe(this, object : Observer<AuthResource<User>> {
            override fun onChanged(user: AuthResource<User>?) {
                if (user != null) {
                    when (user) {
                        is AuthResource.Loading -> showProgressBar(true)
                        is AuthResource.Authenticated -> {
                            showProgressBar(false)
                            Log.d(TAG, "OnChanged: LOGIN SUCCESS ${user.data}")
                        }
                        is AuthResource.Error -> {
                            showProgressBar(false)
                            Toast.makeText(
                                this@AuthActivity,
                                user.message + "\nDid you enter a number 1 and 10",
                                Toast.LENGTH_SHORT
                            ).show()
                        }
                        is AuthResource.Logout -> showProgressBar(false)
                    }
                }
            }
        })
    }

    private fun showProgressBar(isShowing: Boolean) {
        if (isShowing) {
            progress_bar.visibility = View.VISIBLE
        } else progress_bar.visibility = View.GONE
    }

    private fun setLogo() {
        requestManager
            .load(logo)
            .into(login_logo)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.login_button -> {
                attemptLogin()
            }
        }
    }

    private fun attemptLogin() {
        if (TextUtils.isEmpty(user_id_input.text.toString())) {
            return
        }
        viewModel.authenticateWithId(Integer.parseInt(user_id_input.text.toString()))
    }
}
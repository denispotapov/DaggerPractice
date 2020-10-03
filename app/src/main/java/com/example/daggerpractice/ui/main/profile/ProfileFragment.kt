package com.example.daggerpractice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.daggerpractice.R
import com.example.daggerpractice.models.User
import com.example.daggerpractice.ui.auth.AuthResource
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_profile.*
import javax.inject.Inject

class ProfileFragment : DaggerFragment() {
    private val TAG = "ProfileFragment"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: ProfileViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: Profile Fragment was created")
        viewModel = ViewModelProvider(this, providerFactory).get(ProfileViewModel::class.java)
        viewModel.logForProfileViewModel()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.getAuthenticatedUser().removeObservers(viewLifecycleOwner)
        viewModel.getAuthenticatedUser()
            .observe(viewLifecycleOwner, object : Observer<AuthResource<User>> {
                override fun onChanged(user: AuthResource<User>?) {
                    if (user != null) {
                        when (user) {
                            is AuthResource.Authenticated -> {
                                setUserDetails(user.data)
                            }
                            is AuthResource.Error -> {
                                setErrorDetails(user.message)
                            }
                        }
                    }
                }
            })
    }
    private fun setErrorDetails(message: String) {
        email.text = message
        username.text = "error"
        website.text = "error"
    }
    private fun setUserDetails(data: User) {
        email.text = data.email
        username.text = data.username
        website.text = data.website
    }
}
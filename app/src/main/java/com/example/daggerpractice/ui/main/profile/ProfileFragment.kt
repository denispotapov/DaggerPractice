package com.example.daggerpractice.ui.main.profile

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.example.daggerpractice.R
import com.example.daggerpractice.ui.auth.AuthViewModel
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
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
    }
}
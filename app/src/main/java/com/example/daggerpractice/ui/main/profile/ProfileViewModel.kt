package com.example.daggerpractice.ui.main.profile

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.models.User
import com.example.daggerpractice.ui.auth.AuthResource
import javax.inject.Inject

class ProfileViewModel @Inject constructor(private val sessionManager: SessionManager): ViewModel() {
    private val TAG = "ProfileViewModel"

    fun logForProfileViewModel() = Log.d(TAG, "ProfileViewModel: ProfileViewModel is ready...")

    fun getAuthenticatedUser():LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }
}
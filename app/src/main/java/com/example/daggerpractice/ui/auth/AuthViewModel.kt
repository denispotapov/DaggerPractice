package com.example.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.network.auth.AuthApi
import javax.inject.Inject

private const val TAG = "AuthViewModel"

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {
    fun log() = Log.d(TAG, "AuthViewModel: ViewModel is working...")

    fun api() {
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL...")
        } else Log.d(TAG, "AuthViewModel: auth api NOT NULL...")
    }
}

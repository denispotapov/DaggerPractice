package com.example.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject


class AuthViewModel @Inject constructor() : ViewModel() {
    fun log() = Log.d("Model","AuthViewModel: ViewModel is working...")
}

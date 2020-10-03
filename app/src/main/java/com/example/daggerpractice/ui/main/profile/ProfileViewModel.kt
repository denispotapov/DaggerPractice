package com.example.daggerpractice.ui.main.profile

import android.util.Log
import androidx.lifecycle.ViewModel
import javax.inject.Inject

class ProfileViewModel @Inject constructor(): ViewModel() {
    private val TAG = "ProfileViewModel"

    fun logForProfileViewModel() = Log.d(TAG, "ProfileViewModel: ProfileViewModel is ready...")

}
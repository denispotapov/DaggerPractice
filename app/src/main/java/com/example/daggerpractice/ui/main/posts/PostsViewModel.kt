package com.example.daggerpractice.ui.main.posts

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.network.main.MainApi
import javax.inject.Inject

class PostsViewModel @Inject constructor(
    private val sessionManager: SessionManager,
    private val mainApi: MainApi) : ViewModel() {

    private val TAG = "PostsViewModel"

    fun logForPostViewModel() = Log.d(TAG, "PostsViewModel: viewModel is working")

}
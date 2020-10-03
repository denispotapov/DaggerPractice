package com.example.daggerpractice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.example.daggerpractice.R
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class PostsFragments : DaggerFragment() {
    private val TAG = "PostsFragments"

    @Inject
    lateinit var providerFactory: ViewModelProviderFactory
    lateinit var viewModel: PostsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_posts, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        Log.d(TAG, "onViewCreated: PostsFragments was created")
        viewModel = ViewModelProvider(this, providerFactory).get(PostsViewModel::class.java)
        viewModel.logForPostViewModel()
    }
}
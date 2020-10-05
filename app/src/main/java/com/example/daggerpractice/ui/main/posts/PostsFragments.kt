package com.example.daggerpractice.ui.main.posts

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.daggerpractice.R
import com.example.daggerpractice.models.Post
import com.example.daggerpractice.ui.main.Resource
import com.example.daggerpractice.viewmodels.ViewModelProviderFactory
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_posts.*
import javax.inject.Inject

class PostsFragments : DaggerFragment() {
    private val TAG = "PostsFragments"

    @Inject
    lateinit var postAdapter: PostsRecyclerAdapter

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

        initRecycler()
        subscribeObservers()
    }

    private fun subscribeObservers() {
        viewModel.observePost().removeObservers(viewLifecycleOwner)
        viewModel.observePost().observe(
            viewLifecycleOwner,
            object : Observer<Resource<List<Post>>> {
                override fun onChanged(listResource: Resource<List<Post>>?) {
                    if (listResource != null) {
                        when (listResource) {
                            is Resource.Loading -> Log.d(TAG, "onChanged: LOADING...")
                            is Resource.Success -> {
                                Log.d(TAG, "onChanged: got posts ${listResource.data}")
                                postAdapter.setPosts(listResource.data)
                            }
                            is Resource.Error -> Log.d(
                                TAG,
                                "onChanged: Error ${listResource.message}"
                            )
                        }
                    }
                }
            })
    }

    private fun initRecycler() {
        recycler_view.apply {
            adapter = postAdapter
            layoutManager = LinearLayoutManager(activity)
        }
    }
}
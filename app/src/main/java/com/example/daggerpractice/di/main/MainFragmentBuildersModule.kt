package com.example.daggerpractice.di.main

import com.example.daggerpractice.ui.auth.AuthActivity
import com.example.daggerpractice.ui.main.posts.PostsFragments
import com.example.daggerpractice.ui.main.profile.ProfileFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class MainFragmentBuildersModule {

    @ContributesAndroidInjector
    abstract fun contributeProfileFragment(): ProfileFragment

    @ContributesAndroidInjector
    abstract fun contributePostsFragments(): PostsFragments
}

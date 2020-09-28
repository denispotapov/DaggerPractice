package com.example.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.MediatorLiveData
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.models.User
import com.example.daggerpractice.network.auth.AuthApi
import com.example.daggerpractice.util.TAG
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject
import androidx.lifecycle.Observer

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<User>()

    fun authenticateWithId(userId: Int) {
        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .subscribeOn(Schedulers.io())
        )
        authUser.addSource(source, object : Observer<User> {
            override fun onChanged(user: User) {
                authUser.value = user
                authUser.removeSource(source)
            }
        })
    }

    fun observeUser(): LiveData<User> {
        return authUser
    }

    fun log() = Log.d(TAG, "AuthViewModel: ViewModel is working...")

    fun api() {
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL...")
        } else Log.d(TAG, "AuthViewModel: auth api NOT NULL...")
    }
}




package com.example.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.SessionManager
import com.example.daggerpractice.models.User
import com.example.daggerpractice.network.auth.AuthApi
import com.example.daggerpractice.util.TAG
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(
    private val authApi: AuthApi,
    private val sessionManager: SessionManager
) : ViewModel() {

    fun authenticateWithId(userId: Int) {
Log.d(TAG, "authenticateWithId: attempting to login.")
       sessionManager.authenticateWithId(queryUserId(userId))
    }

    private fun queryUserId(userId: Int): LiveData<AuthResource<User>> {
        return LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                    // instead of calling onError, do this
                .onErrorReturn(object : Function<Throwable, User> {
                    override fun apply(t: Throwable): User {
                        return User(-1, null, null, null)
                    }
                })
                    // wrap User object in Authresource
                .map(object : Function<User, AuthResource<User>> {
                    override fun apply(user: User): AuthResource<User> {
                        if (user.id == -1) {
                            return AuthResource.Error("Could not auth")
                        }
                        return AuthResource.Authenticated(user)
                    }
                })
                .subscribeOn(Schedulers.io())
        )
    }

    fun observeAuthState(): LiveData<AuthResource<User>> {
        return sessionManager.getAuthUser()
    }

    fun log() = Log.d(TAG, "AuthViewModel: ViewModel is working...")

    fun api() {
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL...")
        } else Log.d(TAG, "AuthViewModel: auth api NOT NULL...")
    }
}




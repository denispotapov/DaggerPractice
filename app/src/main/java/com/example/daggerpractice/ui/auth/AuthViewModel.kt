package com.example.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.*
import com.example.daggerpractice.models.User
import com.example.daggerpractice.network.auth.AuthApi
import com.example.daggerpractice.util.TAG
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {

    private val authUser = MediatorLiveData<AuthResource<User>>()

    fun authenticateWithId(userId: Int) {
        authUser.value = AuthResource.Loading
        val source = LiveDataReactiveStreams.fromPublisher(
            authApi.getUser(userId)
                .onErrorReturn(object : Function<Throwable, User> {
                    override fun apply(t: Throwable): User {
                        return User(-1, null, null, null)
                    }
                })
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
        authUser.addSource(source, object : Observer<AuthResource<User>> {
            override fun onChanged(user: AuthResource<User>?) {
                authUser.value = user
                authUser.removeSource(source)
            }
        })
    }

    fun observeUser(): LiveData<AuthResource<User>> {
        return authUser
    }

    fun log() = Log.d(TAG, "AuthViewModel: ViewModel is working...")

    fun api() {
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL...")
        } else Log.d(TAG, "AuthViewModel: auth api NOT NULL...")
    }
}




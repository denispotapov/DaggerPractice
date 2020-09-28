package com.example.daggerpractice.ui.auth

import android.util.Log
import androidx.lifecycle.ViewModel
import com.example.daggerpractice.models.User
import com.example.daggerpractice.network.auth.AuthApi
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

private const val TAG = "AuthViewModel"

class AuthViewModel @Inject constructor(private val authApi: AuthApi) : ViewModel() {
    fun log() = Log.d(TAG, "AuthViewModel: ViewModel is working...")

    fun api() {
        if (this.authApi == null) {
            Log.d(TAG, "AuthViewModel: auth api is NULL...")
        } else Log.d(TAG, "AuthViewModel: auth api NOT NULL...")
    }

    fun getUser() {
        authApi.getUser(1)
            .toObservable()
            .subscribeOn(Schedulers.io())
            .subscribe(getObserver())
    }
    private fun getObserver(): io.reactivex.Observer<User> {
        return object : io.reactivex.Observer<User> {
            override fun onSubscribe(d: Disposable) {
            }

            override fun onNext(user: User) {
                Log.d(TAG, "onNext: $user")
            }

            override fun onError(e: Throwable) {
                Log.e(TAG, "onError: " + e.message)
            }

            override fun onComplete() {
                Log.d(TAG, "onComplete")
            }
        }
    }
}




package com.example.daggerpractice.ui.auth

sealed class AuthResource<out R> {

    class Authenticated<out T>(val data: T) : AuthResource<T>()
    class Error(var message: String) : AuthResource<Nothing>()
    object Loading : AuthResource<Nothing>()
    object Logout : AuthResource<Nothing>()
}


package com.example.daggerpractice.di.auth

import com.example.daggerpractice.models.TestClass
import com.example.daggerpractice.network.auth.AuthApi
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import javax.inject.Named
import javax.inject.Singleton

@Module
class AuthModule {
    companion object {
        @AuthScope
        @Provides
            fun provideAuthApi(retrofit: Retrofit): AuthApi {
            return retrofit.create(AuthApi::class.java)
        }

        @AuthScope
        @Provides
        @Named("auth-test")
        fun someTest(): TestClass {
            return TestClass()
        }
    }
}
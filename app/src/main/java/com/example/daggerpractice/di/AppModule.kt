package com.example.daggerpractice.di

import android.app.Application
import android.graphics.drawable.Drawable
import androidx.annotation.NonNull
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.RequestManager
import com.bumptech.glide.request.RequestOptions
import com.example.daggerpractice.R
import com.example.daggerpractice.util.BASE_URL
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
class AppModule {

    companion object {

        @Singleton
        @Provides
        fun provideRetrofitInstance(): Retrofit {
            return Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()
        }

        @Singleton
        @Provides
        fun provideRequestOptions(): RequestOptions {
            return RequestOptions
                .placeholderOf(R.drawable.white_background)
                .error(R.drawable.white_background)
        }

        @Singleton
        @Provides
        fun provideGlideInstance(application: Application, requestOptions: RequestOptions): RequestManager {
            return Glide.with(application)
                .setDefaultRequestOptions(requestOptions)

        }
        @Singleton
        @Provides
        fun provideAppDrawable(application: Application): Drawable {
            return ContextCompat.getDrawable(application, R.drawable.logo)!!
        }
    }
}

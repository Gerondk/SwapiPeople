package com.gkp.people.di

import com.gkp.mya.BuildConfig
import com.gkp.people.data.remote.PeopleApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

private  const val  BASE_URL = "https://swapi.dev/api/"

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Singleton
    @Provides
    fun providesOkhttpClient() : OkHttpClient {
        val loggingInterceptor = HttpLoggingInterceptor().apply {
            level =
                if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
        }
        return OkHttpClient
            .Builder()
            .addInterceptor(loggingInterceptor)
            .build()
    }

    @Provides
    @Singleton
    fun providesRetrofit(client: OkHttpClient): Retrofit {

        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun providesNewsApi(retrofit: Retrofit): PeopleApi {
        return retrofit.create(PeopleApi::class.java)
    }

}
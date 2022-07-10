package com.example.data.di

import com.apollographql.apollo3.ApolloClient
import com.apollographql.apollo3.network.okHttpClient
import com.example.data.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
abstract class NetworkModule {

//    @Provides
//    @Singleton
//    fun apolloClient() : ApolloClient {
//        val okHttpClient = OkHttpClient.Builder().build()
//        return ApolloClient.Builder()
//            .serverUrl(BASE_URL)
//            .okHttpClient(okHttpClient)
//            .build()
//    }

    //TODO I don't know why, but it don't compile "ComponentProcessingStep was unable to process 'com.example.countriesapp.di.AppComponent' because 'com.apollographql.apollo3.ApolloClient' could not be resolved."
    //TODO This is my first time working with apollo
}
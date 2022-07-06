package com.example.countriesapp

import android.app.Application
import com.example.countriesapp.di.AppComponent
import com.example.countriesapp.di.DaggerAppComponent
import javax.inject.Inject
import javax.inject.Named

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().application(this).build()
        super.onCreate()
    }
}

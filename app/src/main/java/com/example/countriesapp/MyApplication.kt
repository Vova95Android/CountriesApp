package com.example.countriesapp

import android.app.Application
import com.example.countriesapp.di.AppComponent
import com.example.countriesapp.di.DaggerAppComponent

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().application(this.applicationContext).build()
        super.onCreate()
    }
}

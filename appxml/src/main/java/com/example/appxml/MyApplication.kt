package com.example.appxml

import android.app.Application
import com.example.appxml.di.AppComponent
import com.example.appxml.di.DaggerAppComponent

class MyApplication: Application() {

    lateinit var appComponent: AppComponent

    override fun onCreate() {
        appComponent = DaggerAppComponent.builder().application(this.applicationContext).build()
        super.onCreate()
    }
}

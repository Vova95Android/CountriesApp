package com.example.countriesapp.di

import android.app.Application
import android.content.Context
import com.example.countriesapp.MyApplication
import com.example.countriesapp.ui.MainActivity
import com.example.data.di.NetworkModule
import com.example.data.di.RepositoryModule
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ViewModelModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent  {

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun application(application: Application): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> appComponent
        else -> this.applicationContext.appComponent
    }
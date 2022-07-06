package com.example.countriesapp.di

import android.app.Application
import android.content.Context
import com.example.countriesapp.MainActivity
import com.example.countriesapp.MyApplication
import dagger.BindsInstance
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(
    modules = [ViewModelModule::class]
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
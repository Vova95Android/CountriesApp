package com.example.appxml.di

import android.content.Context
import com.example.appxml.MyApplication
import com.example.appxml.ui.activity.MainActivity
import com.example.appxml.ui.countryDetail.CountryDetailFragment
import com.example.appxml.ui.countryList.CountryListFragment
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
        fun application(@BindsInstance context: Context): Builder

        fun build(): AppComponent
    }

    fun inject(activity: MainActivity)
    fun inject(fragment: CountryListFragment)
    fun inject(fragment: CountryDetailFragment)

}

val Context.appComponent: AppComponent
    get() = when(this) {
        is MyApplication -> appComponent
        else -> this.applicationContext.appComponent
    }
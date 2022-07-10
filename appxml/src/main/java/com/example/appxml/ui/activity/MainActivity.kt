package com.example.appxml.ui.activity

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.appxml.R
import com.example.appxml.di.appComponent
import javax.inject.Inject

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        appComponent.inject(this)
        setContentView(R.layout.activity_main)
    }
}
package com.kotlin_research.kotlinresearch

import android.app.Application
import com.kotlin_research.kotlinresearch.di.AppComponent
import com.kotlin_research.kotlinresearch.di.DaggerAppComponent
import com.kotlin_research.kotlinresearch.di.module.RoomModule
import com.kotlin_research.kotlinresearch.di.module.SharedPreferencesModule

class App : Application() {

    companion object {
        private lateinit var component: AppComponent
        fun getComponent(): AppComponent {
            return component
        }
    }

    override fun onCreate() {
        super.onCreate()
        component = DaggerAppComponent.builder()
                .sharedPreferencesModule(SharedPreferencesModule(applicationContext))
                .roomModule(RoomModule(applicationContext))
                .build()
    }
}
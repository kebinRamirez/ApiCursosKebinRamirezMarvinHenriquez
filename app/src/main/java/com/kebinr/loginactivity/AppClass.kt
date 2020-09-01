package com.kebinr.loginactivity

import android.app.Application
import com.kebinr.loginactivity.util.PreferenceProvider

class AppClass : Application() {
    override fun onCreate() {
        super.onCreate()
        PreferenceProvider.initialize(this)
    }
}
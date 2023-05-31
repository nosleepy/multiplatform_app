package com.grandstream.multiplatform.android

import android.app.Application
import com.grandstream.multiplatform.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class MultiApplication : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@MultiApplication)
        }
    }
}
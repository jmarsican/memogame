package com.javiermarsicano.memogame

import android.app.Application
import timber.log.Timber

class MemoGameApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }
}
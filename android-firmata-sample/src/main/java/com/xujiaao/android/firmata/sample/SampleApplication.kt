package com.xujiaao.android.firmata.sample

import android.app.Application
import com.squareup.leakcanary.LeakCanary

class SampleApplication : Application() {

    override fun onCreate() {
        super.onCreate()

        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }

        LeakCanary.install(this)
    }
}
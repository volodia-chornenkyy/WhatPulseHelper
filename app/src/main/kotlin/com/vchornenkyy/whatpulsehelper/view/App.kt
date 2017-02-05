package com.vchornenkyy.whatpulsehelper.view

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.vchornenkyy.whatpulsehelper.BuildConfig
import com.vchornenkyy.whatpulsehelper.view.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.view.tracking.trackers.TrackerAnswers
import io.fabric.sdk.android.Fabric
import io.paperdb.Paper

class App : Application() {

    var eventTracker: EventTracker? = null

    override fun onCreate() {
        super.onCreate()

        Paper.init(this)

        // setup release build
        if (!BuildConfig.DEBUG) {

            Fabric.with(this, Crashlytics(), Answers())

            eventTracker = EventTracker.instance
            eventTracker?.addTracker(TrackerAnswers())
        }
    }
}

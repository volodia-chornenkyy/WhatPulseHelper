package com.vchornenkyy.whatpulsehelper

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.common.tracking.trackers.TrackerAnswers
import io.fabric.sdk.android.Fabric

class App : Application() {

    var eventTracker: EventTracker? = null

    override fun onCreate() {
        super.onCreate()
        Fabric.with(this, Crashlytics(), Answers())

        // setup tracking only for release build
        if (!BuildConfig.DEBUG) {
            eventTracker = EventTracker.instance
            eventTracker?.addTracker(TrackerAnswers())
        }
    }
}

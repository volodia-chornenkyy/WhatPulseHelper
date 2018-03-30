package com.vchornenkyy.whatpulsehelper

import android.app.Application
import com.crashlytics.android.Crashlytics
import com.crashlytics.android.answers.Answers
import com.google.firebase.perf.FirebasePerformance
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.common.tracking.trackers.TrackerAnswers
import com.vchornenkyy.whatpulsehelper.common.tracking.trackers.TrackerFirebase
import io.fabric.sdk.android.Fabric

class App : Application() {

    var eventTracker: EventTracker? = null

    override fun onCreate() {
        super.onCreate()

        // setup release build
        if (!BuildConfig.DEBUG) {

            Fabric.with(this, Crashlytics(), Answers())

            eventTracker = EventTracker.instance
            eventTracker?.addTracker(TrackerAnswers())
            eventTracker?.addTracker(TrackerFirebase(this))
        }

        FirebasePerformance.getInstance().isPerformanceCollectionEnabled = true
    }
}

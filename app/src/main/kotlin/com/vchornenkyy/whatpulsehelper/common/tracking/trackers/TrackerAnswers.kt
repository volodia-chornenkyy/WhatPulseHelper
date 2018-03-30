package com.vchornenkyy.whatpulsehelper.common.tracking.trackers

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.crashlytics.android.answers.CustomEvent
import com.crashlytics.android.answers.LoginEvent
import com.vchornenkyy.whatpulsehelper.common.tracking.IEventTracker
import com.vchornenkyy.whatpulsehelper.common.tracking.Screens

class TrackerAnswers : IEventTracker {

    override fun login() {
        Answers.getInstance().logLogin(LoginEvent())
    }

    override fun logout() {
        Answers.getInstance().logCustom(CustomEvent("Logout"))
    }

    override fun profileOpened() {
        trackScreen(Screens.PROFILE)
    }

    override fun computersOpened() {
        trackScreen(Screens.COMPUTERS)
    }

    override fun teamsOpened() {
        trackScreen(Screens.TEAMS)
    }

    override fun orientationChanged(orientation: String) {
        Answers.getInstance().logCustom(CustomEvent("Orientation change").putCustomAttribute("type", orientation))
    }

    override fun trackScreen(screen: String) {
        Answers.getInstance().logContentView(ContentViewEvent().putContentName(screen))
    }
}

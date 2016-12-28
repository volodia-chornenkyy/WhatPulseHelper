package com.vchornenkyy.whatpulsehelper.common.tracking.trackers

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.crashlytics.android.answers.CustomEvent
import com.crashlytics.android.answers.LoginEvent
import com.vchornenkyy.whatpulsehelper.common.tracking.IEventTracker

class TrackerAnswers : IEventTracker {

    override fun login() {
        Answers.getInstance().logLogin(LoginEvent())
    }

    override fun logout() {
        Answers.getInstance().logCustom(CustomEvent("Logout"))
    }

    override fun profileOpened() {
        Answers.getInstance().logContentView(ContentViewEvent().putContentName("profile"))
    }

    override fun computersOpened() {
        Answers.getInstance().logContentView(ContentViewEvent().putContentName("computers"))
    }

    override fun orientationChanged(orientation: String) {
        Answers.getInstance().logCustom(CustomEvent("Orientation change").putCustomAttribute("type", orientation))
    }
}

package com.vchornenkyy.whatpulsehelper.common.tracking

import com.crashlytics.android.answers.Answers
import com.crashlytics.android.answers.ContentViewEvent
import com.crashlytics.android.answers.CustomEvent
import com.crashlytics.android.answers.LoginEvent

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
}

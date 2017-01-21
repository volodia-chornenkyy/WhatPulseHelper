package com.vchornenkyy.whatpulsehelper.common.tracking

import java.util.*

class EventTracker : IEventTracker {
    private val trackers: ArrayList<IEventTracker> = ArrayList<IEventTracker>()

    private constructor()

    private object Holder {

        val INSTANCE = EventTracker()
    }

    companion object {

        val instance: EventTracker by lazy { Holder.INSTANCE }
    }

    fun addTracker(tracker: IEventTracker) {
        trackers.add(tracker)
    }

    override fun login() {
        for (tracker in trackers) {
            tracker.login()
        }
    }

    override fun logout() {
        for (tracker in trackers) {
            tracker.logout()
        }
    }

    override fun profileOpened() {
        for (tracker in trackers) {
            tracker.profileOpened()
        }
    }

    override fun computersOpened() {
        for (tracker in trackers) {
            tracker.computersOpened()
        }
    }

    override fun teamsOpened() {
        for (tracker in trackers) {
            tracker.teamsOpened()
        }
    }

    override fun orientationChanged(orientation: String) {
        for (tracker in trackers) {
            tracker.orientationChanged(orientation)
        }
    }
}
package com.vchornenkyy.whatpulsehelper.view.tracking

import com.vchornenkyy.whatpulsehelper.view.tracking.annotations.Orientation

interface IEventTracker {

    fun login()

    fun logout()

    fun profileOpened()

    fun computersOpened()

    fun teamsOpened()

    fun orientationChanged(@Orientation.Items orientation: String)
}
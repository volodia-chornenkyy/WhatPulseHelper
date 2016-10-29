package com.vchornenkyy.whatpulsehelper.common.tracking

import com.vchornenkyy.whatpulsehelper.common.tracking.annotations.Orientation

interface IEventTracker {

    fun login()

    fun logout()

    fun profileOpened()

    fun orientationChanged(@Orientation.Items orientation: String)
}
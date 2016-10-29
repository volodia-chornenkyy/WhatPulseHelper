package com.vchornenkyy.whatpulsehelper.common

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.common.tracking.annotations.Orientation

abstract class BaseActivity : AppCompatActivity() {

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            EventTracker.instance.orientationChanged(Orientation.LANDSCAPE)
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            EventTracker.instance.orientationChanged(Orientation.PORTRAIT)
        }
    }
}
package com.vchornenkyy.whatpulsehelper.common.tracking.annotations

import android.support.annotation.StringDef

class Orientation {

    companion object {
        const val PORTRAIT = "portrait"
        const val LANDSCAPE = "orientation"
    }

    @StringDef(PORTRAIT, LANDSCAPE)
    @Retention(AnnotationRetention.SOURCE)
    annotation class Items
}
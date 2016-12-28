package com.vchornenkyy.whatpulsehelper.common

import android.os.Bundle
import android.support.v4.app.Fragment

abstract class BaseFragment : Fragment(), IBaseView {
    var sharedView: IBaseView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        sharedView = SharedView(this)
    }

    override fun displayMessage(message: String) {
        sharedView?.displayMessage(message)
    }
}
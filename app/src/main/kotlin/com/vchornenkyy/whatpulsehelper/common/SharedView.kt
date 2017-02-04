package com.vchornenkyy.whatpulsehelper.common

import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.View

class SharedView : BaseView {

    private var activity: BaseActivity? = null
    private var fragment: Fragment? = null

    constructor(activity: BaseActivity) {
        this.activity = activity
    }

    constructor(fragment: Fragment) {
        this.fragment = fragment
    }

    override fun displayMessage(message: String) {
        if (activity != null) {
            activity?.let {
                showSnackbar(it.findViewById(android.R.id.content), message)
            }
        } else if (fragment != null && fragment?.view != null) {
            fragment?.view?.let {
                showSnackbar(it, message)
            }
        } else {
            throw IllegalArgumentException("Activity or fragment should be passed")
        }
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }

}

package com.vchornenkyy.whatpulsehelper.view

import android.app.Activity
import android.support.design.widget.Snackbar
import android.view.View

class SharedView(private val activity: Activity) : BaseView {

    override fun displayMessage(message: String) {
        showSnackbar(activity.findViewById(android.R.id.content), message)
    }

    private fun showSnackbar(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show();
    }
}

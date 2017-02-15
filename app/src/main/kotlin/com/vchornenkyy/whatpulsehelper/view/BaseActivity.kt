package com.vchornenkyy.whatpulsehelper.view

import android.content.res.Configuration
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.view.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.view.tracking.annotations.Orientation

abstract class BaseActivity : AppCompatActivity(), BaseView {

    var sharedView: BaseView? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedView = SharedView(this)
        initPresenter()
    }

    override fun onDestroy() {
        super.onDestroy()
        removePresenter()
    }

    override fun onConfigurationChanged(newConfig: Configuration?) {
        super.onConfigurationChanged(newConfig)

        // Checks the orientation of the screen
        if (newConfig?.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            EventTracker.instance.orientationChanged(Orientation.LANDSCAPE)
        } else if (newConfig?.orientation == Configuration.ORIENTATION_PORTRAIT) {
            EventTracker.instance.orientationChanged(Orientation.PORTRAIT)
        }
    }

    override fun displayMessage(message: String) {
        sharedView?.displayMessage(message)
    }

    // TODO create Uber-FragmentManager for this boilerplate
    protected fun openFragment(fragment: Fragment) {
        supportFragmentManager
                .beginTransaction()
                .replace(R.id.mainActivityContainer, fragment)
                .commit()
    }

    protected fun getFirstVisibleFragment(): Fragment? {
        supportFragmentManager.fragments
                ?.filter { it != null && it.isVisible }
                ?.forEach { return it }
        return null
    }

    protected open fun initPresenter() {}

    protected open fun removePresenter() {}
}
package com.vchornenkyy.whatpulsehelper.view

import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

abstract class BaseFragment : Fragment(), BaseView {
    var sharedView: BaseView? = null

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        sharedView = SharedView(this)

        initPresenter()

        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()

        removePresenter()
    }

    override fun displayMessage(message: String) {
        sharedView?.displayMessage(message)
    }

    protected open fun initPresenter() {}

    protected open fun removePresenter() {}
}
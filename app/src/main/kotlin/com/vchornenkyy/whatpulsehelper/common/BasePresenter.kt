package com.vchornenkyy.whatpulsehelper.common

import android.support.annotation.CallSuper

abstract class BasePresenter<VIEW> {

    protected var view: VIEW? = null

    @CallSuper
    open fun attach(view: VIEW) {
        this.view = view
    }

    @CallSuper
    open fun detach() {
        view = null
    }
}
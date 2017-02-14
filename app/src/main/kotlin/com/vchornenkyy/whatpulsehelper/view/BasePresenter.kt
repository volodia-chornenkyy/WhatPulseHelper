package com.vchornenkyy.whatpulsehelper.view

import android.support.annotation.CallSuper
import rx.subscriptions.CompositeSubscription

abstract class BasePresenter<VIEW> {

    protected var view: VIEW? = null

    protected val compositeSubscription: CompositeSubscription = CompositeSubscription()

    @CallSuper
    open fun attach(view: VIEW) {
        this.view = view
    }

    @CallSuper
    open fun detach() {
        view = null

        if (compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe()
        }
    }
}
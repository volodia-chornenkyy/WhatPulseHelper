package com.vchornenkyy.whatpulsehelper.view

import android.support.annotation.CallSuper
import rx.subscriptions.CompositeSubscription

abstract class BasePresenter<VIEW>(
        protected val view: VIEW) {

    protected val compositeSubscription: CompositeSubscription = CompositeSubscription()

    @CallSuper
    open fun attach() {
    }

    @CallSuper
    open fun detach() {
        if (compositeSubscription.hasSubscriptions()) {
            compositeSubscription.unsubscribe()
        }
    }
}
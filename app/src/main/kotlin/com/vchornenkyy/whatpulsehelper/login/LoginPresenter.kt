package com.vchornenkyy.whatpulsehelper.login

import com.vchornenkyy.whatpulsehelper.helper.AppProperties
import rx.Subscription

class LoginPresenter constructor(val appProperties: AppProperties) {

    private var view: LoginView? = null
    private var userSubscription: Subscription? = null

    fun login(username: String) {
        if (username.length == 0) {
            view?.displayMessage("Please enter username")
            return
        }

        // todo load user to check if it exist
    }

    fun attach(view: LoginView) {
        this.view = view
    }

    fun detach() {
        view = null

        userSubscription?.unsubscribe()
        userSubscription = null
    }

}
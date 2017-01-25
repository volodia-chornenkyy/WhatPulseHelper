package com.vchornenkyy.whatpulsehelper.splash

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties

class SplashPresenter constructor(private val appProperties: AppProperties) {

    private var view: SplashView? = null

    fun openRespectableScreen() {
        if (appProperties.getUsername().isEmpty()) {
            view?.openLoginScreen()
        } else {
            view?.openMainScreen()
        }
    }

    fun attach(view: SplashView) {
        this.view = view
    }

    fun detach() {
        view = null
    }
}
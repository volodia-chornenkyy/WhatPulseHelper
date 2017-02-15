package com.vchornenkyy.whatpulsehelper.view.screens.splash

import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.view.BasePresenter

class SplashPresenter<VIEW : SplashPresenter.View>(
        private val appProperties: AppProperties,
        view: VIEW) : BasePresenter<VIEW>(view) {

    fun openRespectableScreen() {
        if (appProperties.getUsername().isEmpty()) {
            view.openLoginScreen()
        } else {
            view.openMainScreen()
        }
    }

    interface View {

        fun openMainScreen()

        fun openLoginScreen()
    }
}
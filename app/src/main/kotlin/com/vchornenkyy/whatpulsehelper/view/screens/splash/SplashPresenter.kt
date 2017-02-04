package com.vchornenkyy.whatpulsehelper.view.screens.splash

import com.vchornenkyy.whatpulsehelper.domain.BasePresenter
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties

class SplashPresenter<VIEW : SplashPresenter.View> constructor(private val appProperties: AppProperties) : BasePresenter<VIEW>() {

    fun openRespectableScreen() {
        if (appProperties.getUsername().isEmpty()) {
            view?.openLoginScreen()
        } else {
            view?.openMainScreen()
        }
    }

    interface View {

        fun openMainScreen()

        fun openLoginScreen()
    }
}
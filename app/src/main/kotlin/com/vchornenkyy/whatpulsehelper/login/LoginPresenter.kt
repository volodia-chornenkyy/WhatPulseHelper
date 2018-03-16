package com.vchornenkyy.whatpulsehelper.login

import android.util.Log
import com.fasterxml.jackson.databind.JsonMappingException
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.general_info.GeneralInfoPresenter
import com.vchornenkyy.whatpulsehelper.login.usecase.LoginUseCase
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

class LoginPresenter constructor(val appProperties: AppProperties,
                                 val eventTracker: EventTracker) {

    private var view: LoginView? = null
    private var userSubscription: Disposable? = null

    fun login(username: String) {
        if (username.isEmpty()) {
            view?.displayMessage("Please enter username")
            return
        }

        view?.showProgress(true)

        LoginUseCase(appProperties).execute(username)
                .subscribe(
                        { user ->
                            appProperties.saveUsername(username)

                            eventTracker.login()

                            view?.openMainScreen()
                        },
                        { error ->
                            view?.showProgress(false)

                            if (error is UnknownHostException) {
                                view?.displayMessage("Please check internet connection")
                            } else if (error is JsonMappingException) {
                                view?.displayMessage("Unknown user ID")
                            } else {
                                Log.e(GeneralInfoPresenter::class.java.name, error.message, error)
                            }
                        }
                )
    }

    fun attach(view: LoginView) {
        this.view = view
    }

    fun detach() {
        view = null

        userSubscription?.dispose()
        userSubscription = null
    }

}
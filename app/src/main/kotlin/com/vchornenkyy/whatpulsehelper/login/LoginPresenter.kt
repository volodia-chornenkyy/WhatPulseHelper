package com.vchornenkyy.whatpulsehelper.login

import android.util.Log
import com.fasterxml.jackson.databind.JsonMappingException
import com.vchornenkyy.whatpulsehelper.common.BasePresenter
import com.vchornenkyy.whatpulsehelper.common.BaseView
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.general_info.GeneralInfoPresenter
import com.vchornenkyy.whatpulsehelper.login.usecase.LoginUseCase
import rx.Subscription
import java.net.UnknownHostException

class LoginPresenter<VIEW : LoginPresenter.View> constructor(val appProperties: AppProperties,
                                                   val eventTracker: EventTracker) : BasePresenter<VIEW>() {

    private var userSubscription: Subscription? = null

    fun login(username: String) {
        if (username.isEmpty()) {
            view?.displayMessage("Please enter username")
            return
        }

        view?.showProgress(true)

        LoginUseCase(appProperties).execute(username)
                .subscribe(
                        { isLoggedIn ->

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


    override fun detach() {
        super.detach()

        userSubscription?.unsubscribe()
        userSubscription = null
    }

    interface View : BaseView {

        fun getUsername(): String

        fun showProgress(show: Boolean)

        fun openMainScreen()
    }

}
package com.vchornenkyy.whatpulsehelper.view.screens.login

import android.util.Log
import com.fasterxml.jackson.databind.JsonMappingException
import com.vchornenkyy.whatpulsehelper.domain.BasePresenter
import com.vchornenkyy.whatpulsehelper.domain.LoginUseCase
import com.vchornenkyy.whatpulsehelper.view.BaseView
import com.vchornenkyy.whatpulsehelper.view.screens.general_info.GeneralInfoPresenter
import rx.Subscription
import java.net.UnknownHostException

class LoginPresenter<VIEW : LoginPresenter.View> constructor(private val loginUseCase: LoginUseCase) : BasePresenter<VIEW>() {

    private var userSubscription: Subscription? = null

    fun login(username: String) {
        if (username.isEmpty()) {
            view?.displayMessage("Please enter username")
            return
        }

        view?.showProgress(true)

        loginUseCase.execute(username)
                .subscribe(
                        { isLoggedIn ->
                            view?.showProgress(false)
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
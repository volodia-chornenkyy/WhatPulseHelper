package com.vchornenkyy.whatpulsehelper.view.screens.login

import com.vchornenkyy.whatpulsehelper.domain.BasePresenter
import com.vchornenkyy.whatpulsehelper.domain.LoginUseCase
import com.vchornenkyy.whatpulsehelper.view.BaseView
import rx.Subscription

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

                            view?.displayError(error)
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

        fun displayError(e: Throwable) // TODO move to BaseView in case if it will work well
    }
}
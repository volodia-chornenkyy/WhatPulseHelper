package com.vchornenkyy.whatpulsehelper.view.screens.login

import com.vchornenkyy.whatpulsehelper.domain.exceptions.EmptyUsernameException
import com.vchornenkyy.whatpulsehelper.domain.usecases.auth.LoginUseCase
import com.vchornenkyy.whatpulsehelper.view.BasePresenter
import com.vchornenkyy.whatpulsehelper.view.BaseView

class LoginPresenter<VIEW : LoginPresenter.View>(
        private val loginUseCase: LoginUseCase,
        view: VIEW) : BasePresenter<VIEW>(view) {

    fun login(username: String) {
        if (username.isEmpty()) {
            view.displayError(EmptyUsernameException())
            return
        }

        view.showProgress(true)

        val subscription = loginUseCase.execute(username)
                .subscribe(
                        { isLoggedIn ->
                            view.showProgress(false)
                            view.openMainScreen()
                        },
                        { error ->
                            view.showProgress(false)

                            view.displayError(error)
                        }
                )
        compositeSubscription.add(subscription)
    }

    interface View : BaseView {

        fun getUsername(): String

        fun showProgress(show: Boolean)

        fun openMainScreen()

        fun displayError(e: Throwable) // TODO move to BaseView in case if it will work well
    }
}
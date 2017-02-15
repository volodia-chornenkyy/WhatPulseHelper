package com.vchornenkyy.whatpulsehelper.view.screens.general_info

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.domain.usecases.user.GetUserUseCase
import com.vchornenkyy.whatpulsehelper.view.BasePresenter
import com.vchornenkyy.whatpulsehelper.view.BaseView
import java.net.UnknownHostException

class GeneralInfoPresenter<VIEW : GeneralInfoPresenter.View>(
        private val appProperties: AppProperties,
        view: VIEW) : BasePresenter<VIEW>(view) {

    fun loadUser() {
        val subscription = GetUserUseCase(appProperties).execute()
                .subscribe(
                        { user ->
                            view.bindUser(user)
                        },
                        { error ->

                            if (error is UnknownHostException) {
                                view.displayMessage("Please check internet connection")
                            }

                            Log.e(GeneralInfoPresenter::class.java.name, error.message, error)
                            // TODO display error message to UI
                        }
                )
        compositeSubscription.add(subscription)
    }

    interface View : BaseView {
        fun updateUserData()

        fun bindUser(user: User)
    }
}

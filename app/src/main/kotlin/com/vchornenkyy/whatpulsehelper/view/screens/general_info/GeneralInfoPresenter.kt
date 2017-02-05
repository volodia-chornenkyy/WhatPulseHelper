package com.vchornenkyy.whatpulsehelper.view.screens.general_info

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.domain.usecases.GetUserUseCase
import com.vchornenkyy.whatpulsehelper.view.BasePresenter
import com.vchornenkyy.whatpulsehelper.view.BaseView
import rx.Subscription
import java.net.UnknownHostException

class GeneralInfoPresenter<VIEW : GeneralInfoPresenter.View> constructor(val appProperties: AppProperties) : BasePresenter<VIEW>() {

    var userSubscription: Subscription? = null

    fun loadUser() {
        userSubscription = GetUserUseCase(appProperties).execute()
                .subscribe(
                        { user ->
                            view?.bindUser(user)
                        },
                        { error ->

                            if (error is UnknownHostException) {
                                view?.displayMessage("Please check internet connection")
                            }

                            Log.e(GeneralInfoPresenter::class.java.name, error.message, error)
                            // TODO display error message to UI
                        }
                )
    }

    override fun detach() {
        super.detach()

        userSubscription?.unsubscribe()
        userSubscription = null
    }

    interface View : BaseView {
        fun bindUser(user: User)
    }
}

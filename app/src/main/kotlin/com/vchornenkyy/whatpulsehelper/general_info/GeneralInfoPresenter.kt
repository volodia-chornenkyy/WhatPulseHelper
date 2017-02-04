package com.vchornenkyy.whatpulsehelper.general_info

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.BasePresenter
import com.vchornenkyy.whatpulsehelper.common.BaseView
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.general_info.usecase.GetUserUseCase
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

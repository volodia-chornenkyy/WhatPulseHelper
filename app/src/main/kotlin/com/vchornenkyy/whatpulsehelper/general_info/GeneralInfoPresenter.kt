package com.vchornenkyy.whatpulsehelper.general_info

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.general_info.usecase.GetUserUseCase
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

class GeneralInfoPresenter constructor(val appProperties: AppProperties) {

    var view: GeneralInfoView? = null
    var userSubscription: Disposable? = null

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

    fun detach() {
        userSubscription?.dispose()
        userSubscription = null
    }
}

package com.vchornenkyy.whatpulsehelper.general_info

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.UseCaseFactory
import com.vchornenkyy.whatpulsehelper.general_info.usecase.GetUserUseCase
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException
import java.text.DateFormat
import java.util.*

class GeneralInfoPresenter constructor(val appProperties: AppProperties,
                                       val useCaseFactory: UseCaseFactory) {

    var view: GeneralInfoView? = null
    private var userSubscription: Disposable? = null

    fun loadUser() {
        userSubscription = useCaseFactory.get(GetUserUseCase::class.java)!!.execute()
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

    fun forceLoadUser() {
        if (appProperties.isUserLoadingAvailable(Date().time)) {
            showLoadingInfo()
        } else {
            view?.hideLoadingInfo()
            loadUser()
        }
    }

    fun detach() {
        userSubscription?.dispose()
        userSubscription = null
    }

    fun onLoadingInfoDismissed() {
        view?.hideLoadingInfo()
    }

    private fun showLoadingInfo() {
        val lastUserLoadTimestamp = appProperties.getLastUserLoadTimestamp()
        val dateInstance = DateFormat.getDateInstance(DateFormat.DEFAULT, appProperties.getAppLocale())
        val timeInstance = DateFormat.getTimeInstance(DateFormat.SHORT, appProperties.getAppLocale())
        val date = Date(lastUserLoadTimestamp)
        view?.showLoadingInfo(dateInstance.format(date), timeInstance.format(date))
    }
}

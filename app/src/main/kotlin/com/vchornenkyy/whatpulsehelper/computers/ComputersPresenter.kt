package com.vchornenkyy.whatpulsehelper.computers

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.computers.usecase.GetComputersUseCase
import rx.Subscription
import java.net.UnknownHostException

class ComputersPresenter constructor(val appProperties: AppProperties) {

    var view: ComputersView? = null
    var subscription: Subscription? = null

    fun loadComputers() {
        subscription = GetComputersUseCase(appProperties).execute()
                .subscribe(
                        { computers ->
                            view?.bindComputers(computers)
                        },
                        { error ->

                            if (error is UnknownHostException) {
                                view?.displayMessage("Please check internet connection")
                            }

                            Log.e(ComputersPresenter::class.java.name, error.message, error)
                            // TODO display error message to UI
                        }
                )
    }

    fun detach() {
        subscription?.unsubscribe()
        subscription = null
    }
}
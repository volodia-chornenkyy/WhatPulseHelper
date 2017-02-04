package com.vchornenkyy.whatpulsehelper.computers

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.BasePresenter
import com.vchornenkyy.whatpulsehelper.common.BaseView
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.computers.usecase.GetComputersUseCase
import rx.Subscription
import java.net.UnknownHostException

class ComputersPresenter<VIEW : ComputersPresenter.View> constructor(val appProperties: AppProperties) : BasePresenter<VIEW>() {

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

    override fun detach() {
        super.detach()

        subscription?.unsubscribe()
        subscription = null
    }

    interface View : BaseView {
        fun bindComputers(computers: List<Computer>)
    }
}

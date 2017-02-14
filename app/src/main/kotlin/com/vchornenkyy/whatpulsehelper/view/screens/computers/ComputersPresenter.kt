package com.vchornenkyy.whatpulsehelper.view.screens.computers

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.dto.Computer
import com.vchornenkyy.whatpulsehelper.domain.usecases.computer.GetComputersUseCase
import com.vchornenkyy.whatpulsehelper.view.BasePresenter
import com.vchornenkyy.whatpulsehelper.view.BaseView
import java.net.UnknownHostException

class ComputersPresenter<VIEW : ComputersPresenter.View>(
        private val appProperties: AppProperties) : BasePresenter<VIEW>() {

    fun loadComputers() {
        val subscription = GetComputersUseCase(appProperties).execute()
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
        compositeSubscription.add(subscription)
    }

    interface View : BaseView {
        fun updateComputersData()

        fun bindComputers(computers: List<Computer>)
    }
}

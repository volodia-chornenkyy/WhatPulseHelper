package com.vchornenkyy.whatpulsehelper.computers

import android.util.Log
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.UseCaseFactory
import com.vchornenkyy.whatpulsehelper.computers.usecase.GetComputersUseCase
import io.reactivex.disposables.Disposable
import java.net.UnknownHostException

class ComputersPresenter constructor(val appProperties: AppProperties,
                                     val useCaseFactory: UseCaseFactory) {

    var view: ComputersView? = null
    var subscription: Disposable? = null

    fun loadComputers() {
        subscription = useCaseFactory.get(GetComputersUseCase::class.java)!!.execute()
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
        subscription?.dispose()
        subscription = null
    }
}

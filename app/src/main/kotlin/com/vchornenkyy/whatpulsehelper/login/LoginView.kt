package com.vchornenkyy.whatpulsehelper.login

import com.vchornenkyy.whatpulsehelper.common.IBaseView

interface LoginView : IBaseView {

    fun getUsername(): String

    fun showProgress(show: Boolean)

    fun openMainScreen()

}
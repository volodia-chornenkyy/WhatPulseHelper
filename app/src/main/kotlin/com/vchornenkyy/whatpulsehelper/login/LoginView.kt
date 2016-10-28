package com.vchornenkyy.whatpulsehelper.login

interface LoginView {

    fun getUsername(): String

    fun displayMessage(message: String)

}
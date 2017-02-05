package com.vchornenkyy.whatpulsehelper.view.screens.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import com.fasterxml.jackson.databind.JsonMappingException
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.domain.LoginUseCase
import com.vchornenkyy.whatpulsehelper.view.BaseActivity
import com.vchornenkyy.whatpulsehelper.view.MainActivity
import com.vchornenkyy.whatpulsehelper.view.tracking.EventTracker
import kotlinx.android.synthetic.main.login_layout.*
import java.net.UnknownHostException

class LoginActivity : BaseActivity(), LoginPresenter.View {


    var presenter: LoginPresenter<LoginPresenter.View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login_layout)

        presenter = LoginPresenter(LoginUseCase(SharedPrefAppProperties(this)))

        presenter?.attach(this)

        loginProceed.setOnClickListener {
            presenter?.login(loginUsername.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }

    override fun showProgress(show: Boolean) {
        if (show) {
            loginProceed.visibility = View.GONE
            loginProgress.visibility = View.VISIBLE
        } else {
            loginProceed.visibility = View.VISIBLE
            loginProgress.visibility = View.GONE
        }
    }

    override fun getUsername(): String {
        return loginUsername.text.toString()
    }

    override fun openMainScreen() {
        EventTracker.instance.login()

        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }

    override fun displayError(e: Throwable) {
        when (e) {
            is UnknownHostException -> displayMessage("Please check internet connection")
            is JsonMappingException -> displayMessage("Unknown user ID")
            else -> Log.e(LoginActivity::class.java.canonicalName, e.message, e)
        }
    }
}
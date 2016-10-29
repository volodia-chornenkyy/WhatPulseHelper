package com.vchornenkyy.whatpulsehelper.login

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import com.vchornenkyy.whatpulsehelper.MainActivity
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity(), LoginView {

    var presenter: LoginPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        presenter = LoginPresenter(
                SharedPrefAppProperties(this),
                EventTracker.instance)

        presenter?.attach(this)

        loginProceed.setOnClickListener {
            presenter?.login(loginUsername.text.toString())
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }

    override fun displayMessage(message: String) {
        Snackbar.make(findViewById(android.R.id.content), message, Snackbar.LENGTH_SHORT).show()
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
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}
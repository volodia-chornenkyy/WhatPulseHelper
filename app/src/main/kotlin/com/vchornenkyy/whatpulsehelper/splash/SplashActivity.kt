package com.vchornenkyy.whatpulsehelper.splash

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.vchornenkyy.whatpulsehelper.MainActivity
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.login.LoginActivity

class SplashActivity : AppCompatActivity(), SplashView {

    private var presenter: SplashPresenter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter = SplashPresenter(SharedPrefAppProperties(this))
        presenter?.attach(this)

        presenter?.openRespectableScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }

    override fun openLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
}

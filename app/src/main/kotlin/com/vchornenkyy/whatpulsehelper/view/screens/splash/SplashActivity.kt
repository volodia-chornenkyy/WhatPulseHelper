package com.vchornenkyy.whatpulsehelper.view.screens.splash

import android.content.Intent
import android.os.Bundle
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.view.BaseActivity
import com.vchornenkyy.whatpulsehelper.view.MainActivity
import com.vchornenkyy.whatpulsehelper.view.screens.login.LoginActivity

class SplashActivity : BaseActivity(), SplashPresenter.View {

    private var presenter: SplashPresenter<SplashPresenter.View>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        presenter?.openRespectableScreen()
    }

    override fun onDestroy() {
        super.onDestroy()
        presenter?.detach()
    }

    override fun initPresenter() {
        presenter = SplashPresenter(SharedPrefAppProperties(this), this)
        presenter?.attach()
    }

    override fun removePresenter() {
        presenter?.detach()
    }

    //region View
    override fun openLoginScreen() {
        startActivity(Intent(this, LoginActivity::class.java))
        finish()
    }

    override fun openMainScreen() {
        startActivity(Intent(this, MainActivity::class.java))
        finish()
    }
    //endregion
}

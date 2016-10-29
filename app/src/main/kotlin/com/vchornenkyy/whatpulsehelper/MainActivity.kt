package com.vchornenkyy.whatpulsehelper

import android.content.Intent
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import com.vchornenkyy.whatpulsehelper.common.BaseActivity
import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.InMemoryCache
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.general_info.GeneralInfoFragment
import com.vchornenkyy.whatpulsehelper.login.LoginActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity() {

    private var appProperties: AppProperties? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appProperties = SharedPrefAppProperties(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            openFragment(GeneralInfoFragment.newInstance())
        }

        bottomBar.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.tab_profile -> {
                    openFragment(GeneralInfoFragment.newInstance())

                    EventTracker.instance.profileOpened()
                }
                R.id.tab_computers, R.id.tab_teams -> Toast.makeText(this, "Coming soon", Snackbar.LENGTH_SHORT).show()
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_logout -> {
                // todo move to presenter

                appProperties?.saveUsername("")

                val cache: Cache = InMemoryCache.instance
                cache.clear()

                EventTracker.instance.logout()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return false
    }


    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityContainer, fragment)
                .commit()
    }
}

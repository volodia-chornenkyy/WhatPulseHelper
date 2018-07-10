package com.vchornenkyy.whatpulsehelper

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.vchornenkyy.whatpulsehelper.common.BaseActivity
import com.vchornenkyy.whatpulsehelper.common.IRefreshable
import com.vchornenkyy.whatpulsehelper.common.api.Cache
import com.vchornenkyy.whatpulsehelper.common.api.SharedPrefCache
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.common.tracking.EventTracker
import com.vchornenkyy.whatpulsehelper.computers.ComputersFragment
import com.vchornenkyy.whatpulsehelper.general_info.GeneralInfoFragment
import com.vchornenkyy.whatpulsehelper.login.LoginActivity
import com.vchornenkyy.whatpulsehelper.teams.TeamsFragment
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private var appProperties: AppProperties? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)

        appProperties = SharedPrefAppProperties(this)

        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        if (savedInstanceState == null) {
            openFragment(GeneralInfoFragment.newInstance())
        } else {
            updateToolbarTitle(getCurrentFragment())
        }

        bottomBar.setOnNavigationItemSelectedListener { item ->
            var fragment: Fragment? = null
            when (item.itemId) {
                R.id.tab_profile -> {
                    fragment = GeneralInfoFragment.newInstance()

                    EventTracker.instance.profileOpened()
                }
                R.id.tab_computers -> {
                    fragment = ComputersFragment.newInstance()

                    EventTracker.instance.computersOpened()
                }
                R.id.tab_teams -> {
                    fragment = TeamsFragment.newInstance()

                    EventTracker.instance.computersOpened()
                }
            }
            updateToolbarMenu(fragment!!)
            openFragment(fragment)
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_refresh -> {
                val fragment = getCurrentFragment()
                if (fragment is IRefreshable) {
                    fragment.onRefresh()
                }
            }
            R.id.menu_logout -> {
                // todo move to presenter

                appProperties?.saveUsername("")

                val cache: Cache = SharedPrefCache(this, appProperties!!)
                cache.clear()

                EventTracker.instance.logout()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return false
    }

    override fun onAttachFragment(fragment: Fragment) {
        super.onAttachFragment(fragment)
        updateToolbarTitle(fragment)
    }

    private fun getCurrentFragment() =
            supportFragmentManager.findFragmentById(R.id.mainActivityContainer)

    private fun updateToolbarTitle(fragment: Fragment) {
        if (bottomBar == null) {
            return
        }
        var title: String? = null
        when (fragment) {
            is GeneralInfoFragment -> title = bottomBar.menu.getItem(0).title as String
            is ComputersFragment -> title = bottomBar.menu.getItem(1).title as String
            is TeamsFragment -> title = bottomBar.menu.getItem(2).title as String
        }
        toolbar.title = title?.toUpperCase()
    }

    private fun updateToolbarMenu(fragment: Fragment) {
        if (bottomBar == null) {
            return
        }
        when (fragment) {
            is GeneralInfoFragment -> toolbar.menu.getItem(0).isVisible = true
            is ComputersFragment -> toolbar.menu.getItem(0).isVisible = false
            is TeamsFragment -> toolbar.menu.getItem(0).isVisible = false
        }
    }

    private fun openFragment(fragment: Fragment) {
        val currentFragment = getCurrentFragment()
        val sameFragment = currentFragment != null && fragment::class.java.name == currentFragment::class.java.name
        if (!sameFragment) {
            supportFragmentManager.beginTransaction()
                    .replace(R.id.mainActivityContainer, fragment)
                    .commit()
        }
    }
}

package com.vchornenkyy.whatpulsehelper.view

import android.content.Intent
import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.Menu
import android.view.MenuItem
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.UserPaperCache
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.view.screens.computers.ComputersFragment
import com.vchornenkyy.whatpulsehelper.view.screens.general_info.GeneralInfoFragment
import com.vchornenkyy.whatpulsehelper.view.screens.login.LoginActivity
import com.vchornenkyy.whatpulsehelper.view.screens.teams.TeamsFragment
import com.vchornenkyy.whatpulsehelper.view.tracking.EventTracker
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
        }

        bottomBar.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.tab_profile -> {
                    openFragment(GeneralInfoFragment.newInstance())

                    EventTracker.instance.profileOpened()
                }
                R.id.tab_computers -> {
                    openFragment(ComputersFragment.newInstance())

                    EventTracker.instance.computersOpened()
                }
                R.id.tab_teams -> {
                    openFragment(TeamsFragment.newInstance())

                    EventTracker.instance.teamsOpened()
                }
            }
            return@setOnNavigationItemSelectedListener true
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

                val cache: BaseCache<UserResponse> = UserPaperCache()
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

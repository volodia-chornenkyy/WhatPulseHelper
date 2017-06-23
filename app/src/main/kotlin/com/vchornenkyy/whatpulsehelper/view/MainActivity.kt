package com.vchornenkyy.whatpulsehelper.view

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.helper.SharedPrefAppProperties
import com.vchornenkyy.whatpulsehelper.domain.cache.BaseCache
import com.vchornenkyy.whatpulsehelper.domain.cache.boilerplate.UserResponsePaperCache
import com.vchornenkyy.whatpulsehelper.domain.usecases.GetCurrentVersionUseCase
import com.vchornenkyy.whatpulsehelper.model.api.pojo.UserResponse
import com.vchornenkyy.whatpulsehelper.view.screens.computers.ComputersFragment
import com.vchornenkyy.whatpulsehelper.view.screens.computers.ComputersPresenter
import com.vchornenkyy.whatpulsehelper.view.screens.general_info.GeneralInfoFragment
import com.vchornenkyy.whatpulsehelper.view.screens.general_info.GeneralInfoPresenter
import com.vchornenkyy.whatpulsehelper.view.screens.login.LoginActivity
import com.vchornenkyy.whatpulsehelper.view.screens.teams.TeamFragment
import com.vchornenkyy.whatpulsehelper.view.tracking.EventTracker
import kotlinx.android.synthetic.main.main_activity.*

class MainActivity : BaseActivity() {

    private var appProperties: AppProperties? = null

    private var menu: Menu? = null

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
            val firstVisibleFragment = getFirstVisibleFragment()
            when (item.itemId) {
                R.id.tab_profile -> {
                    if (firstVisibleFragment !is GeneralInfoPresenter.View) {
                        openFragment(GeneralInfoFragment.newInstance())

                        EventTracker.instance.profileOpened()
                    } else {
                        firstVisibleFragment.updateUserData()
                    }
                }
                R.id.tab_computers -> {
                    if (firstVisibleFragment !is ComputersPresenter.View) {
                        openFragment(ComputersFragment.newInstance())

                        EventTracker.instance.computersOpened()
                    } else {
                        firstVisibleFragment.updateComputersData()
                    }
                }
                R.id.tab_teams -> {
                    if (firstVisibleFragment !is TeamFragment) {
                        openFragment(TeamFragment.newInstance())

                        EventTracker.instance.teamsOpened()
                    } else {
                        firstVisibleFragment.updateTeamData()
                    }
                }
            }
            return@setOnNavigationItemSelectedListener true
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu, menu)
        this.menu = menu

        // todo move to presenter
        GetCurrentVersionUseCase().execute().subscribe { setCurrentVersion(it) }

        return true
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            R.id.menu_logout -> {
                // todo move to presenter

                appProperties?.saveUsername("")

                val cache: BaseCache<UserResponse> = UserResponsePaperCache()
                cache.clearAll()

                EventTracker.instance.logout()

                startActivity(Intent(this, LoginActivity::class.java))
                finish()
            }
        }
        return false
    }

    //region FUTURE VIEW METHODS
    fun setCurrentVersion(version: String) {
        val versionItem = menu?.findItem(R.id.menu_version)
        versionItem?.title = getString(R.string.menu_version_pattern, version)
    }
    //endregion
}

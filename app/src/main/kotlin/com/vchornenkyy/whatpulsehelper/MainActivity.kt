package com.vchornenkyy.whatpulsehelper

import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        bottomBar.setOnTabSelectListener { tabId ->
            when (tabId) {
                R.id.tab_profile -> openFragment(GeneralInfoFragment.newInstance())
                R.id.tab_computers -> openFragment(Fragment())
                R.id.tab_teams -> openFragment(Fragment())
            }
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityContainer, fragment)
                .commit()
    }
}

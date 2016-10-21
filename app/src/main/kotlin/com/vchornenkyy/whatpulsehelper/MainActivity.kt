package com.vchornenkyy.whatpulsehelper

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        if (savedInstanceState == null) {
            openFragment(GeneralInfoFragment.newInstance())
        }

        bottomBar.setOnNavigationItemSelectedListener {
            item ->
            when (item.itemId) {
                R.id.tab_profile -> openFragment(GeneralInfoFragment.newInstance())
                R.id.tab_computers, R.id.tab_teams -> Toast.makeText(this, "Coming soon", Snackbar.LENGTH_SHORT).show()
            }
            return@setOnNavigationItemSelectedListener false
        }
    }

    private fun openFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.mainActivityContainer, fragment)
                .commit()
    }
}

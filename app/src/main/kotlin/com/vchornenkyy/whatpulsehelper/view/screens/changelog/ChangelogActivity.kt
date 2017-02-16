package com.vchornenkyy.whatpulsehelper.view.screens.changelog

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.vchornenkyy.whatpulsehelper.BuildConfig
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.domain.dto.ChangelogData
import com.vchornenkyy.whatpulsehelper.domain.dto.ChangelogUnit
import com.vchornenkyy.whatpulsehelper.domain.dto.ChangelogVersion
import com.vchornenkyy.whatpulsehelper.view.BaseActivity
import kotlinx.android.synthetic.main.changelog_activity.*
import java.util.*

class ChangelogActivity : BaseActivity() {

    private val adapter = ChangelogAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.changelog_activity)

        setupToolbar()

        setupList()

        setupCurrentVersion()

        val mockChangelogs = ArrayList<ChangelogUnit>()
        for (i in 1..10) {
            mockChangelogs.add(ChangelogVersion("Version 0.3"))
            mockChangelogs.add(ChangelogData("What was changed in this version"))
            mockChangelogs.add(ChangelogVersion("Version 0.2"))
            mockChangelogs.add(ChangelogData("What was changed in this version"))
        }
        adapter.updateData(mockChangelogs)
    }

    private fun setupCurrentVersion() {
        tvCurrentVersion.text = BuildConfig.VERSION_NAME
    }

    private fun setupToolbar() {
        setSupportActionBar(toolbar)
        toolbar.setNavigationOnClickListener {
            onBackPressed()
        }
        supportActionBar?.setDisplayShowTitleEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
    }

    private fun setupList() {
        val list: RecyclerView? = findViewById(R.id.list) as RecyclerView?
        val layoutManager = LinearLayoutManager(this)
        layoutManager.isAutoMeasureEnabled = true
        list?.layoutManager = layoutManager
        list?.isNestedScrollingEnabled = false
        list?.setHasFixedSize(true)
        list?.adapter = adapter
    }
}
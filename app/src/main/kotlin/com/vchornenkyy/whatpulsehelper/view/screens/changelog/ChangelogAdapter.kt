package com.vchornenkyy.whatpulsehelper.view.screens.changelog

import android.support.annotation.LayoutRes
import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.domain.dto.ChangelogData
import com.vchornenkyy.whatpulsehelper.domain.dto.ChangelogUnit
import com.vchornenkyy.whatpulsehelper.domain.dto.ChangelogVersion
import com.vchornenkyy.whatpulsehelper.view.widgets.BaseAdapter

class ChangelogAdapter : BaseAdapter<ChangelogUnit>() {

    @LayoutRes
    override fun getLayoutIdForPosition(position: Int): Int {
        val obj = getObjForPosition(position)
        if (obj is ChangelogVersion) {
            return R.layout.changelog_version_layout
        } else if (obj is ChangelogData) {
            return R.layout.changelog_data_layout
        }
        return 0
    }

}
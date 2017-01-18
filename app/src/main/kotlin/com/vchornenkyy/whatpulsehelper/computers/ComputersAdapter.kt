package com.vchornenkyy.whatpulsehelper.computers

import com.vchornenkyy.whatpulsehelper.R
import com.vchornenkyy.whatpulsehelper.common.dto.Computer
import com.vchornenkyy.whatpulsehelper.common.view.BaseAdapter

class ComputersAdapter : BaseAdapter<Computer>() {

    override fun getLayoutIdForPosition(position: Int): Int {
        return R.layout.computer_layout
    }
}
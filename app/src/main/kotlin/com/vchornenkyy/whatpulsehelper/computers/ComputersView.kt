package com.vchornenkyy.whatpulsehelper.computers

import com.vchornenkyy.whatpulsehelper.common.IBaseView
import com.vchornenkyy.whatpulsehelper.common.ILoadingView
import com.vchornenkyy.whatpulsehelper.common.dto.Computer

interface ComputersView : IBaseView, ILoadingView {
    fun bindComputers(computers: List<Computer>)
}
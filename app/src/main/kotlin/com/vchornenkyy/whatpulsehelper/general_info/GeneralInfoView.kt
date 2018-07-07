package com.vchornenkyy.whatpulsehelper.general_info

import com.vchornenkyy.whatpulsehelper.common.IBaseView
import com.vchornenkyy.whatpulsehelper.common.IRefreshable
import com.vchornenkyy.whatpulsehelper.common.dto.User

interface GeneralInfoView : IBaseView, IRefreshable {
    fun bindUser(user: User)
    fun showLoadingInfo(date: String, time: String)
    fun hideLoadingInfo()
}
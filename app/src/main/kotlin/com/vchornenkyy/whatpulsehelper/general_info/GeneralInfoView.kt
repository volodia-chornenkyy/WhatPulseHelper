package com.vchornenkyy.whatpulsehelper.general_info

import com.vchornenkyy.whatpulsehelper.common.IBaseView
import com.vchornenkyy.whatpulsehelper.common.dto.User

interface GeneralInfoView : IBaseView {
    fun bindUser(user: User)
}
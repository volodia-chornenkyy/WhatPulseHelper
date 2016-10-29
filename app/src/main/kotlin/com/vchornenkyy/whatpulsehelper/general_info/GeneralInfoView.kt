package com.vchornenkyy.whatpulsehelper.general_info

import com.vchornenkyy.whatpulsehelper.common.dto.User

interface GeneralInfoView {
    fun bindUser(user: User)
}
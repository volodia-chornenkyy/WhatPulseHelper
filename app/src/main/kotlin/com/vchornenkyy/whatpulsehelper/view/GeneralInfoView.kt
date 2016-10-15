package com.vchornenkyy.whatpulsehelper.view

import com.vchornenkyy.whatpulsehelper.api.model.UserResponse

interface GeneralInfoView {
    fun bindUser(user: UserResponse)
}
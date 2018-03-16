package com.vchornenkyy.whatpulsehelper.login.usecase

import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import io.reactivex.Observable

class LoginUseCase(appProperties: AppProperties) : BaseUserUseCase(appProperties) {

    fun execute(userId: String): Observable<User> {
        return getUser(userId)
    }
}
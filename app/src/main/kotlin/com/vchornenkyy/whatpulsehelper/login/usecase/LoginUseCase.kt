package com.vchornenkyy.whatpulsehelper.login.usecase

import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import rx.Observable

class LoginUseCase(appProperties: AppProperties) : BaseUserUseCase(appProperties) {

    fun execute(userId: String): Observable<User> {
        return getUser(userId)
                .map { userResponse ->
                    cache.saveUser(userResponse)
                    return@map converter.convert(userResponse)
                }
    }
}
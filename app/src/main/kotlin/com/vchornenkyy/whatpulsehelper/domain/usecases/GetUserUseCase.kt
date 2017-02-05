package com.vchornenkyy.whatpulsehelper.domain.usecases

import com.vchornenkyy.whatpulsehelper.domain.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.domain.usecases.BaseUserUseCase
import rx.Observable

class GetUserUseCase(appProperties: AppProperties) : BaseUserUseCase(appProperties) {
    fun execute(userId: String = properties.getUsername()): Observable<User> {
        return getUser(userId)
    }
}

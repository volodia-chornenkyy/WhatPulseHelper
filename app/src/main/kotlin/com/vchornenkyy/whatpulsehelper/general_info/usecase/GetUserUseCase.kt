package com.vchornenkyy.whatpulsehelper.general_info.usecase

import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import io.reactivex.Observable

class GetUserUseCase(appProperties: AppProperties) : BaseUserUseCase(appProperties) {
    fun execute(userId: String = properties.getUsername()): Observable<User> {
        return getUser(userId)
    }
}

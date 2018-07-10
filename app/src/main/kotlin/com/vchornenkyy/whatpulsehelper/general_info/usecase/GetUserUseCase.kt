package com.vchornenkyy.whatpulsehelper.general_info.usecase

import android.content.Context
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import io.reactivex.Observable

class GetUserUseCase(context: Context, appProperties: AppProperties) : BaseUserUseCase(context, appProperties) {
    fun execute(userId: String = properties.getUsername()): Observable<User> {
        return getUser(userId)
    }
}
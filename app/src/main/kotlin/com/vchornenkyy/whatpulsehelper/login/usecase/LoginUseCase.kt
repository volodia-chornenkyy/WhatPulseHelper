package com.vchornenkyy.whatpulsehelper.login.usecase

import android.content.Context
import com.vchornenkyy.whatpulsehelper.common.dto.User
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.common.usecase.BaseUserUseCase
import io.reactivex.Observable

class LoginUseCase(context: Context, appProperties: AppProperties) : BaseUserUseCase(context, appProperties) {

    fun execute(userId: String): Observable<User> {
        return getUser(userId)
    }
}
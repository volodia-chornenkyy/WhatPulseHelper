package com.vchornenkyy.whatpulsehelper.common.usecase

import android.content.Context
import com.vchornenkyy.whatpulsehelper.common.helper.AppProperties
import com.vchornenkyy.whatpulsehelper.computers.usecase.GetComputersUseCase
import com.vchornenkyy.whatpulsehelper.general_info.usecase.GetUserUseCase
import com.vchornenkyy.whatpulsehelper.login.usecase.LoginUseCase

class UseCaseFactory(val context: Context, val appProperties: AppProperties) {

    inline fun <reified T : BaseUserUseCase> get(clazz: Class<T>): T? {
        when (T::class) {
            GetUserUseCase::class -> return GetUserUseCase(context, appProperties) as T
            GetComputersUseCase::class -> return GetComputersUseCase(context, appProperties) as T
            LoginUseCase::class -> return LoginUseCase(context, appProperties) as T
        }
        return null
    }

}
